package com.pedrero.eclihand.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "AUT_AUTHORIZATION")
@PrimaryKeyJoinColumn(name = "ID")
public class AuthorizationImpl extends DataObjectImpl implements Authorization {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6629314118259894230L;

	@Column(name = "AUT_CREDENTIAL")
	@Enumerated(EnumType.STRING)
	private Credential credential;

	@Override
	public Credential getCredential() {
		return credential;
	}

	public void setName(Credential credential) {
		this.credential = credential;
	}
}
