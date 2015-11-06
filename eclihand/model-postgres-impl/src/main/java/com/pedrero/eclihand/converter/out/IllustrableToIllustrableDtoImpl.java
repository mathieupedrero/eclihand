package com.pedrero.eclihand.converter.out;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.out.IllustrableToIllustrableDto;
import com.pedrero.eclihand.model.domain.Illustrable;
import com.pedrero.eclihand.model.dto.IllustrableDto;

@Component
public class IllustrableToIllustrableDtoImpl implements IllustrableToIllustrableDto {

	@Override
	public IllustrableDto apply(Illustrable t) {
		return null;
	}
}
