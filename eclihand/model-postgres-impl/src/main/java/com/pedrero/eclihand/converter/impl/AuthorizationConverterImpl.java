package com.pedrero.eclihand.converter.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.AuthorizationConverter;
import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.impl.AuthorizationImpl;
import com.pedrero.eclihand.model.dto.AuthorizationDto;

@Component
public class AuthorizationConverterImpl extends
		ConverterImpl<Authorization, AuthorizationDto> implements
		AuthorizationConverter {

	@Override
	public AuthorizationDto convertToDto(Authorization domain) {
		return getDozerBeanMapper().map(domain, AuthorizationDto.class,
				"authorization");
	}

	@Override
	public Authorization convertToEntity(AuthorizationDto dto) {
		return getDozerBeanMapper().map(dto, AuthorizationImpl.class,
				"authorization");
	}

	@Override
	public void lightFeedEntityWithDto(Authorization domain,
			AuthorizationDto dto) {
		getDozerBeanMapper().map(dto, domain, "authorization-light");
	}
}
