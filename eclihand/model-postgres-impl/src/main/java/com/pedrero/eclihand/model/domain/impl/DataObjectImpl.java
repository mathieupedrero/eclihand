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

	private Long id;
	
	private String index;

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

	@Override
	@Id
	@Column(name = "OBJ_INDEX")
	public String getIndex() {
		return index;
	}

	@Override
	public void setIndex(String index) {
		this.index = index;
	}

}