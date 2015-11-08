package com.pedrero.eclihand.converter.out;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.out.AuthorizationToAuthorizationDto;
import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.dto.AuthorizationDto;

@Component
public class AuthorizationToAuthorizationDtoImpl implements AuthorizationToAuthorizationDto {

	@Override
	public AuthorizationDto apply(Authorization t) {
		return null;
	}
}
