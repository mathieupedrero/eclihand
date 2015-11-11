package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.dto.ProfileDto;

@Component
public class ProfileToProfileDtoImpl implements ProfileToProfileDto {

	@Resource
	private AuthorizationToAuthorizationDto authorizationToAuthorizationDto;

	@Override
	public ProfileDto apply(Profile source) {
		if (source == null) {
			return null;
		}
		ProfileDto result = new ProfileDto();
		map(source::getName, result::setName);
		map(source::getAuthorizations, result::setAuthorizations,
				inSet(authorizationToAuthorizationDto));
		return result;
	}
}
