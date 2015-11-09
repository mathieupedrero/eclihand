package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.map;
import static com.pedrero.eclihand.converter.in.InConverterUtils.mapDataObjectFields;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.factory.DomainObjectsFactory;
import com.pedrero.eclihand.model.dto.AuthorizationDto;

@Component
public class AuthorizationDtoToAuthorizationImpl implements AuthorizationDtoToAuthorization {

	@Resource
	private DomainObjectsFactory domainObjectsFactory;

	@Override
	public Authorization apply(AuthorizationDto source) {
		if (source == null) {
			return null;
		}
		Authorization result = domainObjectsFactory.createAuthorization();
		mapDataObjectFields(source, result);
		map(source::getCredential, result::setCredential);
		return result;
	}
}
