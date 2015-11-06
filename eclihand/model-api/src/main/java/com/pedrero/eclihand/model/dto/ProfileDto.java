package com.pedrero.eclihand.model.dto;

import java.util.HashSet;
import java.util.Set;

public class ProfileDto extends IllustrableDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1984471066293805378L;

	private String name;

	private Set<AuthorizationDto> authorizations = new HashSet<AuthorizationDto>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AuthorizationDto> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(Set<AuthorizationDto> authorizations) {
		this.authorizations = authorizations;
	}
}