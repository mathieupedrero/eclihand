package com.pedrero.eclihand.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.pedrero.eclihand.model.domain.UserType;

public class UserDto extends IllustrableDto {

	private String login;

	private String password;

	private UserType userType;

	private Set<ProfileDto> profiles = new HashSet<ProfileDto>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Set<ProfileDto> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<ProfileDto> profiles) {
		this.profiles = profiles;
	}
}