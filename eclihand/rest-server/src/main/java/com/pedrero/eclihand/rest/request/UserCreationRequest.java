package com.pedrero.eclihand.rest.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.pedrero.eclihand.model.dto.UserDto;

public class UserCreationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -808769398272718743L;

	@Valid
	private UserDto userToCreate;

	@NotNull
	private String passwordToken;

	public UserDto getUserToCreate() {
		return userToCreate;
	}

	public void setUserToCreate(UserDto userToCreate) {
		this.userToCreate = userToCreate;
	}

	public String getPasswordToken() {
		return passwordToken;
	}

	public void setPasswordToken(String passwordToken) {
		this.passwordToken = passwordToken;
	}

}
