package com.pedrero.eclihand.model.dto;

import java.util.HashMap;
import java.util.Map;

public class DataObjectDto {

	private Long id;

	private final Map<String, Object> otherProperties = new HashMap<String, Object>();

	public DataObjectDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, Object> getOtherProperties() {
		return otherProperties;
	}

}