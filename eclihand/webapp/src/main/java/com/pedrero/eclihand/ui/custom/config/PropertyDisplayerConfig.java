package com.pedrero.eclihand.ui.custom.config;

import java.util.List;

public class PropertyDisplayerConfig {
	private List<PropertyConfig> properties;

	private Boolean showsEditButtons = false;

	private Boolean isEditModeDefault = false;

	public Boolean getShowsEditButtons() {
		return showsEditButtons;
	}

	public void setShowsEditButtons(Boolean showsEditButtons) {
		this.showsEditButtons = showsEditButtons;
	}

	public Boolean getIsEditModeDefault() {
		return isEditModeDefault;
	}

	public void setIsEditModeDefault(Boolean isEditModeDefault) {
		this.isEditModeDefault = isEditModeDefault;
	}

	public List<PropertyConfig> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyConfig> properties) {
		this.properties = properties;
	}

}
