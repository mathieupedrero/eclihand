package com.pedrero.eclihand.rest.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class EclihandToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643494609806411501L;

	private final Principal principal;
	private final EclihandRequestCredentials credentials;

	public EclihandToken() {
		this(new Principal(null), null);
	}

	private EclihandToken(Principal principal, EclihandRequestCredentials credentials) {
		super(principal, credentials);
		this.principal = principal;
		this.credentials = credentials;
	}

	public EclihandToken(String userName, EclihandRequestCredentials credentials) {
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

	}

}
