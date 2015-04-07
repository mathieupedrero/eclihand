package com.pedrero.eclihand.rest.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643494609806411501L;

	private final Principal principal;
	private final EclihandRequestCredentials credentials;

	public AuthenticationToken() {
		this(new Principal(null), null);
	}

	private AuthenticationToken(Principal principal, EclihandRequestCredentials credentials) {
		super(principal, credentials);
		this.principal = principal;
		this.credentials = credentials;
	}

	public AuthenticationToken(String userName, EclihandRequestCredentials credentials) {
		this(new Principal(userName), credentials);
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public EclihandRequestCredentials getCredentials() {
		return credentials;
	}

	private static final class Principal implements java.security.Principal {
		private final String name;

		private Principal(String name) {
			super();
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "Principal [name=" + name + "]";
		}

	}

}
