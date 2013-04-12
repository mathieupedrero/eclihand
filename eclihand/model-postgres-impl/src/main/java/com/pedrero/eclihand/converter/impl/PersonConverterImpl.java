package com.pedrero.eclihand.converter.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.PersonConverter;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.impl.PersonImpl;
import com.pedrero.eclihand.model.dto.PersonDto;

@Component
public class PersonConverterImpl extends ConverterImpl<Person, PersonDto>
		implements PersonConverter {

	@Override
	public PersonDto convertToDto(Person domain) {
		return getDozerBeanMapper().map(domain, PersonDto.class, "person");
	}

	@Override
	public Person convertToEntity(PersonDto dto) {
		return getDozerBeanMapper().map(dto, PersonImpl.class, "person");
	}

	@Override
	public void lightFeedEntityWithDto(Person domain, PersonDto dto) {
		getDozerBeanMapper().map(dto, domain, "person-light");
	}
}
