package com.pedrero.eclihand.model.dto;

import com.pedrero.eclihand.model.domain.Credential;

public class AuthorizationDto extends DataObjectDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120859563391247783L;

	private Credential credential;

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

}