package com.pedrero.eclihand.model.dto;

import java.util.HashSet;
import java.util.Set;

public class UserDto extends IllustrableDto {

	private String login;

	private String password;

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

	public Set<ProfileDto> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<ProfileDto> profiles) {
		this.profiles = profiles;
	}
}