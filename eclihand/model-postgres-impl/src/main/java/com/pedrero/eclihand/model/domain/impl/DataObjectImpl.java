package com.pedrero.eclihand.model.domain.impl;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.pedrero.eclihand.model.domain.DataObject;

@MappedSuperclass
public class DataObjectImpl implements DataObject {

	private Long id;

	public DataObjectImpl() {
		super();
	}

	@Override
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}