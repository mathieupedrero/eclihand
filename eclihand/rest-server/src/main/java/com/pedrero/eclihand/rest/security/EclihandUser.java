package com.pedrero.eclihand.rest.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class EclihandUser extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6321557762872832147L;

	private final Boolean guestUser;

	public EclihandUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.guestUser = false;
	}

	public EclihandUser(String username, Collection<? extends GrantedAuthority> authorities) {
		super(username, username, authorities);
		this.guestUser = true;
	}

	public Boolean isGuestUser() {
		return guestUser;
	}
}
