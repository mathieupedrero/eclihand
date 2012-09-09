package com.pedrero.eclihand.ui.table.config;

import java.util.List;

public class TableConfig {

	private List<TableColumnConfig> columnConfigs;

	private String captionKey;

	private String lineDescriptionKey;

	private Boolean canRedirectToEntityDisplayer = false;

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
}
