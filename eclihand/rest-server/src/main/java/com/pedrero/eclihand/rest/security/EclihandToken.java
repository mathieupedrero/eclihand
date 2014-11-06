package com.pedrero.eclihand.rest.security;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class EclihandToken extends UsernamePasswordAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8643494609806411501L;

	private final Object principal;
	private final EclihandCredentials credentials;
	private final Date timestamp;

	public EclihandToken(Object principal, EclihandCredentials credentials, Date timestamp) {
		super(principal, credentials);
		this.principal = principal;
		this.credentials = credentials;
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public EclihandCredentials getCredentials() {
		return credentials;
	}

}
