package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.dto.PersonDto;

@Component
public class PersonToPersonDtoImpl implements PersonToPersonDto {

	@Resource
	private DateToLocalDate dateToLocalDate;

	@Override
	public PersonDto apply(Person source) {
		if (source == null) {
			return null;
		}
		PersonDto result = new PersonDto();
		map(source::getFirstName, result::setFirstName);
		map(source::getLastName, result::setLastName);
		map(source::getGender, result::setGender);
		map(source::getBirthDate, result::setBirthDate, dateToLocalDate);
		return result;
	}
}
