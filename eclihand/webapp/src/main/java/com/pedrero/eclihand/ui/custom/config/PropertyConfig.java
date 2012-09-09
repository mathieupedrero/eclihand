package com.pedrero.eclihand.ui.custom.config;

import com.pedrero.eclihand.utils.text.Formatter;

public class PropertyConfig {
	private String labelKey;
	private String valuePath;
	private Formatter formatter;

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

}
