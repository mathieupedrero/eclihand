package com.pedrero.eclihand.model.dto;

import com.pedrero.eclihand.model.domain.Credential;


public class AuthorizationDto extends DataObjectDto {

	private Credential credential;

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

}