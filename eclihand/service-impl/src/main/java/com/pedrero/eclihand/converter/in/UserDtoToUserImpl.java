package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.fromRepository;
import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;
import static com.pedrero.eclihand.converter.in.InConverterUtils.mapIllustrableFields;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.dao.ProfileDao;
import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.domain.factory.DomainObjectsFactory;
import com.pedrero.eclihand.model.dto.UserDto;

@Component
public class UserDtoToUserImpl implements UserDtoToUser {

	@Resource
	private DomainObjectsFactory domainObjectsFactory;

	@Resource
	private ProfileDao profileDao;

	@Override
	public User apply(UserDto source) {
		if (source == null) {
			return null;
		}
		User result = domainObjectsFactory.createUser();
		mapIllustrableFields(source, result);
		map(source::getLogin, result::setLogin);
		map(source::getUserType, result::setUserType);
		map(source::getProfiles, result::setProfiles, inSet(fromRepository(profileDao)));
		return result;
	}
}
