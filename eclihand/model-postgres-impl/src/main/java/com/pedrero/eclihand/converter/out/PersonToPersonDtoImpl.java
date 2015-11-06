package com.pedrero.eclihand.converter.out;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.out.PersonToPersonDto;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.dto.PersonDto;

@Component
public class PersonToPersonDtoImpl implements PersonToPersonDto {

	@Override
	public PersonDto apply(Person t) {
		// TODO Auto-generated method stub
		return null;
	}
}
