package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.dto.UserDto;

@Component
public class UserToUserDtoImpl implements UserToUserDto {

	@Resource
	private ProfileToProfileDto profileToProfileDto;

	@Override
	public UserDto apply(User source) {
		if (source == null) {
			return null;
		}
		UserDto result = new UserDto();
		map(source::getLogin, result::setLogin);
		map(source::getUserType, result::setUserType);
		map(source::getProfiles, result::setProfiles,
				inSet(profileToProfileDto));
		return result;
	}
}
