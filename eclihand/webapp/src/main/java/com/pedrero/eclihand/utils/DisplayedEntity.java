package com.pedrero.eclihand.utils;

import com.pedrero.eclihand.model.dto.DataObjectDto;

public class DisplayedEntity<T extends DataObjectDto> {

	private T entity;

	private String description;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
