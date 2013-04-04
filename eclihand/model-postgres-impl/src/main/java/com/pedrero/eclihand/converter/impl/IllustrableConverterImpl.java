package com.pedrero.eclihand.converter.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.IllustrableConverter;
import com.pedrero.eclihand.model.domain.Illustrable;
import com.pedrero.eclihand.model.domain.impl.IllustrableImpl;
import com.pedrero.eclihand.model.dto.IllustrableDto;

@Component
public class IllustrableConverterImpl extends
		ConverterImpl<Illustrable, IllustrableDto> implements
		IllustrableConverter {

	@Override
	public IllustrableDto convertToDto(Illustrable domain) {
		return getDozerBeanMapper().map(domain, IllustrableDto.class,
				"illustrable");
	}

	@Override
	public Illustrable convertToEntity(IllustrableDto dto) {
		return getDozerBeanMapper().map(dto, IllustrableImpl.class,
				"illustrable");
	}
}
