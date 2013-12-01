package com.pedrero.eclihand.converter.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.ProfileConverter;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.domain.impl.ProfileImpl;
import com.pedrero.eclihand.model.dto.ProfileDto;

@Component
public class ProfileConverterImpl extends ConverterImpl<Profile, ProfileDto>
		implements ProfileConverter {

	@Override
	public ProfileDto convertToDto(Profile domain) {
		return getDozerBeanMapper().map(domain, ProfileDto.class, "profile");
	}

	@Override
	public Profile convertToEntity(ProfileDto dto) {
		return getDozerBeanMapper().map(dto, ProfileImpl.class, "profile");
	}

	@Override
	public void lightFeedEntityWithDto(Profile domain, ProfileDto dto) {
		getDozerBeanMapper().map(dto, domain, "profile-light");
	}
}
