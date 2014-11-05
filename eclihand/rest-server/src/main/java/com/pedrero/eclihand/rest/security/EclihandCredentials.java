package com.pedrero.eclihand.rest.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public final class EclihandCredentials {

	private final String requestData;
	private final String signature;
	private final Collection<? extends GrantedAuthority> authorities;

	public EclihandCredentials(String requestData, String signature, Collection<? extends GrantedAuthority> authorities) {
		this.requestData = requestData;
		this.signature = signature;
		this.authorities = authorities;
	}

	public String getRequestData() {
		return requestData;
	}

	public String getSignature() {
		return signature;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
