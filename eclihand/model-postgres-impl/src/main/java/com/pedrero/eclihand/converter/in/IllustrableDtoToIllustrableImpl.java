package com.pedrero.eclihand.converter.in;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Illustrable;
import com.pedrero.eclihand.model.dto.IllustrableDto;

@Component
public class IllustrableDtoToIllustrableImpl implements IllustrableDtoToIllustrable {

	@Override
	public Illustrable apply(IllustrableDto t) {
		return null;
	}
}
