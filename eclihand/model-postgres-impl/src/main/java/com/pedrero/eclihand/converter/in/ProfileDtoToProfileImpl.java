package com.pedrero.eclihand.converter.in;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.out.ProfileToProfileDto;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.dto.ProfileDto;

@Component
public class ProfileDtoToProfileImpl implements ProfileToProfileDto {

	@Override
	public ProfileDto apply(Profile t) {
		// TODO Auto-generated method stub
		return null;
	}
}
