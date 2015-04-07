package com.pedrero.eclihand.rest.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class RunningSessionAuthenticationToken extends UsernamePasswordAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1753361970690761458L;

	private final String sessionToken;

	public RunningSessionAuthenticationToken(UsernamePasswordAuthenticationToken from, String sessionToken) {
		super(from.getPrincipal(), from.getCredentials(), from.getAuthorities());
		this.sessionToken = sessionToken;
		this.setDetails(from.getDetails());
	}

	public String getSessionToken() {
		return sessionToken;
	}

}
