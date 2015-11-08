package com.pedrero.eclihand.converter.out;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.out.UserToUserDto;
import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.dto.UserDto;

@Component
public class UserToUserDtoImpl implements UserToUserDto {

	@Override
	public UserDto apply(User t) {
		// TODO Auto-generated method stub
		return null;
	}
}
