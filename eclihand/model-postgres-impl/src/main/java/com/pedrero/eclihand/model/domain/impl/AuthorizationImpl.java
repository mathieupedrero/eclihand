package com.pedrero.eclihand.model.domain.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.Credential;

@Entity
@Table(name = "AUT_AUTHORIZATION")
@PrimaryKeyJoinColumn(name = "ID")
public class AuthorizationImpl extends DataObjectImpl implements Authorization {

	@Column(name = "AUT_CREDENTIAL")
	@Enumerated(EnumType.STRING)
	private Credential credential;

	public Credential getCredential() {
		return credential;
	}

	public void setName(Credential credential) {
		this.credential = credential;
	}
}
