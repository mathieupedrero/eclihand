package com.pedrero.eclihand.ui.custom.config;

import com.pedrero.eclihand.utils.EclihandUiException;
import com.pedrero.eclihand.utils.text.Formatter;

public class PropertyConfig {
	private String labelKey;
	private String valuePath;
	private Formatter formatter;
	private String dataType = "java.lang.String";
	private String maxValue;
	private String minValue;

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
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

	public Formatter getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public Class<? extends Object> retrieveClassDataType(){
		try {
			return Class.forName(dataType);
		} catch (ClassNotFoundException e) {
			throw new EclihandUiException("Config exception: dataType defined in PropertyConfig not found", e);
		}
	}

}
