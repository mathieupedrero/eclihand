package com.pedrero.eclihand.ui.table.config;

import com.pedrero.eclihand.ui.config.EnumType;
import com.vaadin.data.util.converter.Converter;

public class TableColumnConfig {

	private String id;

	private String labelKey;

	private String valuePath;

	private EnumType enumType;

	private Boolean isDescriptionParam = false;

	private ITableAction action = null;

	private Converter<String, Object> formatter;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelKey() {
		return labelKey;
	}

	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}

	public String getValuePath() {
		return valuePath;
	}

	public void setValuePath(String valuePath) {
		this.valuePath = valuePath;
	}

	public EnumType getEnumType() {
		return enumType;
	}

	public void setEnumType(EnumType type) {
		this.enumType = type;
	}

	public Boolean getIsDescriptionParam() {
		return isDescriptionParam;
	}

	public void setIsDescriptionParam(Boolean isDescriptionParam) {
		this.isDescriptionParam = isDescriptionParam;
	}

	public ITableAction getAction() {
		return action;
	}

	public void setAction(ITableAction action) {
		this.action = action;
	}

	public Converter<String, Object> getFormatter() {
		return formatter;
	}

	public void setFormatter(Converter<String, Object> formatter) {
		this.formatter = formatter;
	}

}
