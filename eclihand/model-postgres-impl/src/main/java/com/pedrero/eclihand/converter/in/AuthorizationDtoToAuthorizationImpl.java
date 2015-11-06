package com.pedrero.eclihand.converter.in;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.dto.AuthorizationDto;

@Component
public class AuthorizationDtoToAuthorizationImpl implements AuthorizationDtoToAuthorization {

	@Override
	public Authorization apply(AuthorizationDto t) {
		return null;
	}
}
