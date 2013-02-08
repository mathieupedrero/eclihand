package com.pedrero.eclihand.ui.table.config;

import java.util.List;

public class TableConfig {

	private List<TableColumnConfig> columnConfigs;

	private String captionKey;

	private String lineDescriptionKey;

	private Boolean canRedirectToEntityDisplayer = false;

	private Boolean canSelect = false;

	private Boolean canMultiSelect = false;

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

	public List<TableColumnConfig> getColumnConfigs() {
		return columnConfigs;
	}

	public void setColumnConfigs(List<TableColumnConfig> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}

	public String getCaptionKey() {
		return captionKey;
	}

	public void setCaptionKey(String captionKey) {
		this.captionKey = captionKey;
	}

	public Boolean getCanRedirectToEntityDisplayer() {
		return canRedirectToEntityDisplayer;
	}

	public void setCanRedirectToEntityDisplayer(
			Boolean canRedirectToEntityDisplayer) {
		this.canRedirectToEntityDisplayer = canRedirectToEntityDisplayer;
	}

	public String getLineDescriptionKey() {
		return lineDescriptionKey;
	}

	public void setLineDescriptionKey(String lineDescriptionKey) {
		this.lineDescriptionKey = lineDescriptionKey;
	}

	public Boolean getCanSelect() {
		return canSelect;
	}

	public void setCanSelect(Boolean canSelect) {
		this.canSelect = canSelect;
	}

	public Boolean getCanMultiSelect() {
		return canMultiSelect;
	}

	public void setCanMultiSelect(Boolean canMultiSelect) {
		this.canMultiSelect = canMultiSelect;
	}
}
