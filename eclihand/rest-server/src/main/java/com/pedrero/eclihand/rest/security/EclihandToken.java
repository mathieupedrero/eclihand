package com.pedrero.eclihand.rest.security;

import java.util.Date;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class EclihandToken extends AbstractAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8643494609806411501L;

	private final String principal;
	private final EclihandCredentials credentials;
	private final Date timestamp;

	public EclihandToken(String principal, EclihandCredentials credentials, Date timestamp) {
		super(credentials.getAuthorities());
		this.principal = principal;
		this.credentials = credentials;
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public String getPrincipal() {
		return principal;
	}

	@Override
	public EclihandCredentials getCredentials() {
		return credentials;
	}

}
