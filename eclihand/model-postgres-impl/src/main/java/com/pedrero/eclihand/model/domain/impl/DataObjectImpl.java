package com.pedrero.eclihand.model.domain.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.DataObject;

@Entity
@Table(name = "OBJ_OBJECT")
@Inheritance(strategy = InheritanceType.JOINED)
public class DataObjectImpl implements DataObject {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "OBJ_INDEX")
	private String index;

	public DataObjectImpl() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getIndex() {
		return index;
	}

	@Override
	public void setIndex(String index) {
		this.index = index;
	}

}