package com.pedrero.eclihand.model.dto;

public class SortDto {

	private String columnName;

	private Boolean ascending;

	public SortDto() {
		super();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Boolean isAscending() {
		return ascending;
	}

	public void setAscending(Boolean ascending) {
		this.ascending = ascending;
	}

}