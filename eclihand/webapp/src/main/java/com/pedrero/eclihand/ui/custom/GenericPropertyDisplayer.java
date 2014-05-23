package com.pedrero.eclihand.ui.custom;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.mvel2.MVEL;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.custom.config.PropertyDisplayerConfig;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.utils.EclihandUiException;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

/**
 * @author mpedrero
 * 
 *         This component can display properties of any Data Object
 * 
 * @param <T>
 *            {@link DataObjectDto} type to be displayed
 */
public class GenericPropertyDisplayer<T extends DataObjectDto> extends
		AbstractEntityComponent implements EntityDisplayerComponent<T>,
		UpdatableContentDisplayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698759988513402341L;

	/**
	 * The configuration of this Property Displayer
	 */
	private PropertyDisplayerConfig config;

	/**
	 * The Data Object displayed by this entity displayer
	 */
	private T displayed;

	/**
	 * The display of the property displayer (as a grid)
	 */
	private GridLayout layout;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private LocaleContainer localeContainer;

	@Override
	protected void postConstruct() {
		Integer rowNumber = getConfig().getProperties().size();
		layout = eclihandLayoutFactory.createCommonGridLayout(2, rowNumber + 1);
		super.postConstruct();
		setShowButtons(config.getShowsEditButtons());
		setShowDeleteButton(false);

		this.addComponent(layout);

		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			createLabelAndAddItToLineForProperty(currentRow, property);
			currentRow++;
		}
	}

	private void createLabelAndAddItToLineForProperty(Integer currentRow,
			PropertyConfig property) {
		Label label = eclihandUiFactory.createLabel();
		label.setValue(messageResolver.getMessage(property.getLabelKey()));
		layout.addComponent(label, 0, currentRow);
		layout.setComponentAlignment(label, Alignment.MIDDLE_RIGHT);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void display(T entity) {
		displayed = entity;
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Object rawValue = MVEL.eval(property.getValuePath(), displayed);
			if (getUpdatable()) {
				AbstractField field = createEditableComponentAndAddItToLineForProperty(
						currentRow, property);
				if (rawValue != null) {
					field.setValue(rawValue);
				}
			} else {
				Label label = createLabelAndAddItAsValueToLine(currentRow);
				if (property.getFormatter() != null) {
					label.setConverter(property.getFormatter());
				}
				if (rawValue != null) {
					label.setPropertyDataSource(new ObjectProperty<Object>(
							rawValue));
				}
			}
			currentRow++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.ui.custom.UpdatableContentDisplayer#makeUpdatable()
	 */
	@Override
	public void makeUpdatable() {
		setUpdatable(true);
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			createEditableComponentAndAddItToLineForProperty(currentRow,
					property);
			currentRow++;
		}
		display(displayed);
		super.makeUpdatable();
	}

	@SuppressWarnings("rawtypes")
	private AbstractField createEditableComponentAndAddItToLineForProperty(
			Integer lineNumber, PropertyConfig property) {
		AbstractField field = createAndConfigurePropertyComponent(property);
		if (layout.getComponent(1, lineNumber) != null) {
			layout.removeComponent(1, lineNumber);
		}
		layout.addComponent(field, 1, lineNumber);
		layout.setComponentAlignment(field, Alignment.MIDDLE_LEFT);
		return field;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.ui.custom.UpdatableContentDisplayer#makeReadOnly()
	 */
	@Override
	public void makeReadOnly() {
		setUpdatable(false);
		display(displayed);
		super.makeReadOnly();

	}

	private Label createLabelAndAddItAsValueToLine(Integer currentRow) {
		Label value = eclihandUiFactory.createLabel();
		if (layout.getComponent(1, currentRow) != null) {
			layout.removeComponent(1, currentRow);
		}
		layout.addComponent(value, 1, currentRow);
		layout.setComponentAlignment(value, Alignment.MIDDLE_LEFT);
		return value;
	}

	@SuppressWarnings("rawtypes")
	private AbstractField createAndConfigurePropertyComponent(
			PropertyConfig propertyConfig) {
		Class<? extends Object> dataTypeClass = propertyConfig
				.retrieveClassDataType();
		if (dataTypeClass.equals(String.class)
				|| Number.class.isAssignableFrom(dataTypeClass)
				|| dataTypeClass.isEnum() || dataTypeClass.equals(Date.class)) {
			Converter<String, Object> converter = propertyConfig.getFormatter();

			if (propertyConfig.getMaxValue() != null
					&& propertyConfig.getMinValue() != null) {
				ComboBox combo = new ComboBox();
				if (Integer.class.equals(dataTypeClass)) {
					Integer beginning = Integer.valueOf(propertyConfig
							.getMinValue());
					Integer end = Integer.valueOf(propertyConfig.getMaxValue());
					for (Integer i = beginning; i <= end; i++) {
						combo.addItem(i);
						String toDisplay = converter == null ? i.toString()
								: converter.convertToPresentation(i,
										String.class,
										localeContainer.getLocale());
						combo.setItemCaption(i, toDisplay);
					}
				} else if (Long.class.equals(dataTypeClass)) {
					Long beginning = Long.valueOf(propertyConfig.getMinValue());
					Long end = Long.valueOf(propertyConfig.getMaxValue());
					for (Long i = beginning; i <= end; i++) {
						combo.addItem(i);
						String toDisplay = converter == null ? i.toString()
								: converter.convertToPresentation(i,
										String.class,
										localeContainer.getLocale());
						combo.setItemCaption(i, toDisplay);
					}
				}
				return combo;
			}
			if (dataTypeClass.isEnum()) {
				ComboBox combo = new ComboBox();
				for (Object value : dataTypeClass.getEnumConstants()) {
					combo.addItem(value);
					String toDisplay = converter == null ? value.toString()
							: converter.convertToPresentation(value,
									String.class, localeContainer.getLocale());
					combo.setItemCaption(value, toDisplay);
				}
				return combo;
			}
			if (dataTypeClass.equals(Date.class)) {
				return new DateField();
			}
			return new TextField();
		}
		throw new EclihandUiException("Configuration exception : datatype "
				+ propertyConfig.getDataType() + " not handled in Config");
	}

	@Override
	public void makeCreateMode() {
		this.makeUpdatable();
	}

	@Override
	public void validateChanges() {
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			@SuppressWarnings("rawtypes")
			Field value = (Field) layout.getComponent(1, currentRow);
			Object rawValue = value.getValue();
			MVEL.setProperty(displayed, property.getValuePath(), rawValue);
			currentRow++;
		}
	}

	public PropertyDisplayerConfig getConfig() {
		return config;
	}

	public void setConfig(PropertyDisplayerConfig config) {
		this.config = config;
	}

	@Override
	public List<UpdatableContentDisplayer> getContentDisplayers() {
		return null;
	}

	@Override
	public Layout getMainLayout() {
		return layout;
	}

	@Override
	public Set<Credential> getRequiredCredentials() {
		return null;
	}

	@Override
	public void delete() {
		throw new NotImplementedException();
	}

}
