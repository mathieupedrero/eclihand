package com.pedrero.eclihand.model.domain.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.Authorization;

@Entity
@Table(name = "AUT_AUTHORIZATION")
@PrimaryKeyJoinColumn(name = "ID")
public class AuthorizationImpl extends DataObjectImpl implements Authorization {

	@Column(name = "AUT_NAME")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
