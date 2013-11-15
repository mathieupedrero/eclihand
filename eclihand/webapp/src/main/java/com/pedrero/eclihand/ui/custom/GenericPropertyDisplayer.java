package com.pedrero.eclihand.ui.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pedrero.eclihand.controller.GenericPropertyDisplayerController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.custom.config.PropertyDisplayerConfig;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.utils.EclihandUiException;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.UpdatableContentController;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
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
		AbstractEntityComponent implements Initiable,
		EntityDisplayerComponent<T>, UpdatableContentDisplayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698759988513402341L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GenericPropertyDisplayer.class);

	private GenericPropertyDisplayerController<T> genericPropertyDisplayerController;

	/**
	 * The configuration of this Property Displayer
	 */
	private PropertyDisplayerConfig config;

	/**
	 * Properties to display in the component.
	 */
	private final List<String> propertiesToDisplay = new ArrayList<String>();

	/**
	 * The Data Object displayed by this entity displayer
	 */
	private T displayed;

	/**
	 * The layout of the property displayer
	 */
	private FormLayout layout;

	/**
	 * The field group used for data binding
	 */
	private FieldGroup fieldGroup;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private LocaleContainer localeContainer;

	@Override
	public void display(T entity) {
		displayed = entity;
		BeanItem<T> item = new BeanItem<T>(entity, Arrays.asList());
		for (String property : propertiesToDisplay){
			item.addNestedProperty(property);
		}
		fieldGroup.setItemDataSource(item);
		for (Field<?> field : fieldGroup.getFields()){
			field.setReadOnly(getUpdatable());
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
		for (Field<?> field : fieldGroup.getFields()){
			field.setReadOnly(false);
		}
		super.makeUpdatable();
	}

	@SuppressWarnings("rawtypes")
	private AbstractField createEditableComponentForProperty(
			PropertyConfig property) {
		AbstractField field = createAndConfigurePropertyComponent(property);
		field.setCaption(messageResolver.getMessage(property.getLabelKey()));
		layout.addComponent(field);
		// layout.setComponentAlignment(field, Alignment.MIDDLE_LEFT);
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
		for (Field<?> field : fieldGroup.getFields()){
			field.setReadOnly(true);
		}
		super.makeReadOnly();

	}

	@SuppressWarnings("rawtypes")
	private AbstractField createAndConfigurePropertyComponent(
			PropertyConfig propertyConfig) {

		Field<? extends Object> field = null;
		Class<? extends Object> dataTypeClass = propertyConfig
				.retrieveClassDataType();
		toto: if (dataTypeClass.equals(String.class)
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
						Object toDisplay = converter == null ? i : converter
								.convertToPresentation(i,
										localeContainer.getLocale());
						combo.setItemCaption(i, toDisplay.toString());
					}
				} else if (Long.class.equals(dataTypeClass)) {
					Long beginning = Long.valueOf(propertyConfig.getMinValue());
					Long end = Long.valueOf(propertyConfig.getMaxValue());
					for (Long i = beginning; i <= end; i++) {
						combo.addItem(i);
						Object toDisplay = converter == null ? i : converter
								.convertToPresentation(i,
										localeContainer.getLocale());
						combo.setItemCaption(i, toDisplay.toString());
					}
				}
				field = combo;
				break toto;
			}
			if (dataTypeClass.isEnum()) {
				ComboBox combo = new ComboBox();
				for (Object value : dataTypeClass.getEnumConstants()) {
					combo.addItem(value);
					Object toDisplay = converter == null ? value : converter
							.convertToPresentation(value,
									localeContainer.getLocale());
					combo.setItemCaption(value, toDisplay.toString());
				}

				field = combo;
				break toto;
			}
			if (dataTypeClass.equals(Date.class)) {

				field = new DateField();
				break toto;
			}
			field = new TextField();
			break toto;
		}
		if (field == null) {
			throw new EclihandUiException("Configuration exception : datatype "
					+ propertyConfig.getDataType() + " not handled in Config");
		}
		fieldGroup.bind(field, propertyConfig.getValuePath());
		return (AbstractField) field;
	}

	@Override
	public void makeCreateMode() {
		this.makeUpdatable();
	}

	public void validateChanges() {
		try {
			fieldGroup.commit();
		} catch (CommitException e) {
			LOGGER.error("erreur au commit du FieldGroup", e);
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
	public UpdatableContentController getController() {
		return genericPropertyDisplayerController;
	}

	@Override
	public Layout getMainLayout() {
		return layout;
	}

	@PostConstruct
	protected void postConstruct() {
		for (PropertyConfig property : config.getProperties()) {
			propertiesToDisplay.add(property.getValuePath());
		}

		setShowButtons(config.getShowsEditButtons());
		setShowDeleteButton(false);

		layout = eclihandLayoutFactory.createFormLayout();
		fieldGroup = new FieldGroup();
		setCompositionRoot(layout);

		for (PropertyConfig property : getConfig().getProperties()) {
			createEditableComponentForProperty(property);
		}
		super.postConstruct();
	}

	/**
	 * @param genericPropertyDisplayerController
	 *            the genericPropertyDisplayerController to set
	 */
	public void setGenericPropertyDisplayerController(
			GenericPropertyDisplayerController<T> genericPropertyDisplayerController) {
		this.genericPropertyDisplayerController = genericPropertyDisplayerController;
	}

}
