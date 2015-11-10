package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.map;
import static com.pedrero.eclihand.converter.out.OutConverterUtils.mapDatoObjectFields;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.dto.AuthorizationDto;

@Component
public class AuthorizationToAuthorizationDtoImpl implements AuthorizationToAuthorizationDto {

	@Override
	public AuthorizationDto apply(Authorization source) {
		if (source == null) {
			return null;
		}
		AuthorizationDto result = new AuthorizationDto();
		mapDatoObjectFields(source, result);
		map(source::getCredential, result::setCredential);
		return result;
	}
}
