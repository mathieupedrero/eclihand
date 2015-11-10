package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.fromRepository;
import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;
import static com.pedrero.eclihand.converter.in.InConverterUtils.mapIllustrableFields;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.dao.AuthorizationDao;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.domain.factory.DomainObjectsFactory;
import com.pedrero.eclihand.model.dto.ProfileDto;

@Component
public class ProfileDtoToProfileImpl implements ProfileDtoToProfile {

	@Resource
	private DomainObjectsFactory domainObjectsFactory;

	@Resource
	private AuthorizationDao authorizationDao;

	@Override
	public Profile apply(ProfileDto source) {
		if (source == null) {
			return null;
		}
		Profile result = domainObjectsFactory.createProfile();
		mapIllustrableFields(source, result);
		map(source::getName, result::setName);
		map(source::getAuthorizations, result::setAuthorizations, inSet(fromRepository(authorizationDao)));
		return result;
	}
}
