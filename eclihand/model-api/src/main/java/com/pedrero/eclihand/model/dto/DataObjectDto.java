package com.pedrero.eclihand.model.dto;

import java.util.HashMap;
import java.util.Map;

public class DataObjectDto {

	private Long id;
	
	private String index;

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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Map<String, Object> getOtherProperties() {
		return otherProperties;
	}

}