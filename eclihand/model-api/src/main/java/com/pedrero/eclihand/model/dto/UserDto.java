package com.pedrero.eclihand.model.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pedrero.eclihand.model.domain.UserType;

public class UserDto extends IllustrableDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3700956477005349287L;

	@Size(min = 6, max = 32)
	@NotNull
	private String login;

	@Size(min = 3, max = 254)
	@NotNull
	private String mailAddress;

	private UserType userType;

	private Set<ProfileDto> profiles = new HashSet<ProfileDto>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
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