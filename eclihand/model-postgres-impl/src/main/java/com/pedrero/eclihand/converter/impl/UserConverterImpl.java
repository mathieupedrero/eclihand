package com.pedrero.eclihand.converter.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.UserConverter;
import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.domain.impl.UserImpl;
import com.pedrero.eclihand.model.dto.UserDto;

@Component
public class UserConverterImpl extends ConverterImpl<User, UserDto> implements
		UserConverter {

	@Override
	public UserDto convertToDto(User domain) {
		return getDozerBeanMapper().map(domain, UserDto.class, "user");
	}

	@Override
	public User convertToEntity(UserDto dto) {
		return getDozerBeanMapper().map(dto, UserImpl.class, "user");
	}

	@Override
	public void lightFeedEntityWithDto(User domain, UserDto dto) {
		getDozerBeanMapper().map(dto, domain, "user-light");
	}
}
