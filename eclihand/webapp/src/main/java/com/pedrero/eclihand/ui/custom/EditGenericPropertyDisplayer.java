package com.pedrero.eclihand.ui.custom;

import java.util.Date;

import javax.annotation.Resource;

import org.mvel2.MVEL;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.panel.entity.EditMode;
import com.pedrero.eclihand.utils.EclihandUiException;
import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class EditGenericPropertyDisplayer<T extends DataObjectDto> extends
		AbstractGenericPropertyDisplayer<DataObjectDto> {

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private LocaleContainer localeContainer;

	public EditGenericPropertyDisplayer(EditMode editMode, T edited) {
		super(editMode, edited);
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected void createAndAddComponent(Integer currentRow, PropertyConfig property, Object rawValue) {
		AbstractField field = createEditableComponentAndAddItToLineForProperty(currentRow, property);
		if (rawValue != null) {
			field.setValue(rawValue);
		}

	}

	@SuppressWarnings("rawtypes")
	private AbstractField createEditableComponentAndAddItToLineForProperty(Integer lineNumber, PropertyConfig property) {
		AbstractField field = createAndConfigurePropertyComponent(property);
		getMainLayout().addComponent(field, 1, lineNumber);
		getMainLayout().setComponentAlignment(field, Alignment.MIDDLE_LEFT);
		return field;
	}

	@SuppressWarnings("rawtypes")
	private AbstractField createAndConfigurePropertyComponent(PropertyConfig propertyConfig) {
		Class<? extends Object> dataTypeClass = propertyConfig.retrieveClassDataType();
		if (dataTypeClass.equals(String.class) || Number.class.isAssignableFrom(dataTypeClass)
				|| dataTypeClass.isEnum() || dataTypeClass.equals(Date.class)) {
			Converter<String, Object> converter = propertyConfig.getFormatter();

			if (propertyConfig.getMaxValue() != null && propertyConfig.getMinValue() != null) {
				ComboBox combo = new ComboBox();
				if (Integer.class.equals(dataTypeClass)) {
					Integer beginning = Integer.valueOf(propertyConfig.getMinValue());
					Integer end = Integer.valueOf(propertyConfig.getMaxValue());
					for (Integer i = beginning; i <= end; i++) {
						combo.addItem(i);
						String toDisplay = converter == null ? i.toString() : converter.convertToPresentation(i,
								String.class, localeContainer.getLocale());
						combo.setItemCaption(i, toDisplay);
					}
				} else if (Long.class.equals(dataTypeClass)) {
					Long beginning = Long.valueOf(propertyConfig.getMinValue());
					Long end = Long.valueOf(propertyConfig.getMaxValue());
					for (Long i = beginning; i <= end; i++) {
						combo.addItem(i);
						String toDisplay = converter == null ? i.toString() : converter.convertToPresentation(i,
								String.class, localeContainer.getLocale());
						combo.setItemCaption(i, toDisplay);
					}
				}
				return combo;
			}
			if (dataTypeClass.isEnum()) {
				ComboBox combo = new ComboBox();
				for (Object value : dataTypeClass.getEnumConstants()) {
					combo.addItem(value);
					String toDisplay = converter == null ? value.toString() : converter.convertToPresentation(value,
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
		throw new EclihandUiException("Configuration exception : datatype " + propertyConfig.getDataType()
				+ " not handled in Config");
	}

	public void validateChanges() {
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			@SuppressWarnings("rawtypes")
			Field value = (Field) getMainLayout().getComponent(1, currentRow);
			Object rawValue = value.getValue();
			MVEL.setProperty(getDisplayed(), property.getValuePath(), rawValue);
			currentRow++;
		}
	}

}
