package com.pedrero.eclihand.rest.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class EclihandToken extends UsernamePasswordAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8643494609806411501L;

	private final Object principal;
	private final EclihandRequestCredentials credentials;

	public EclihandToken(Object principal, EclihandRequestCredentials credentials) {
		super(principal, credentials);
		this.principal = principal;
		this.credentials = credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public EclihandRequestCredentials getCredentials() {
		return credentials;
	}

}
