package com.pedrero.eclihand.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.pedrero.eclihand.model.dto.UserDto;

@org.springframework.stereotype.Component(value = "bodyPanel")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Authentication {
	
	private UserDto authenticatedUser;

	public UserDto getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(UserDto authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

}
