package com.pedrero.eclihand.service;

import java.util.Set;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.dto.UserDto;

public interface UserService extends DataObjectService<UserDto> {
	
	public Set<Credential> retrieveCredentialsFor(UserDto user);

}
