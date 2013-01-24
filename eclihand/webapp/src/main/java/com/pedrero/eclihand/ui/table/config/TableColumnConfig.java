package com.pedrero.eclihand.ui.table.config;

import com.pedrero.eclihand.ui.config.EnumType;
import com.pedrero.eclihand.utils.text.Formatter;

public class TableColumnConfig {

	private String id;

	private String labelKey;

	private String valuePath;

	private EnumType enumType;

	private Boolean isDescriptionParam = false;

	private Boolean isLink = false;

	private Formatter formatter;

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

	public Boolean getIsLink() {
		return isLink;
	}

	public void setIsLink(Boolean isLink) {
		this.isLink = isLink;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

}
