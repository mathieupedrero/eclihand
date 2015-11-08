package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.factory.DomainObjectsFactory;
import com.pedrero.eclihand.model.dto.PersonDto;

@Component
public class PersonDtoToPersonImpl implements PersonDtoToPerson {

	@Resource
	private DomainObjectsFactory domainObjectsFactory;

	@Resource
	private LocalDateToDate localDateToDate;

	@Override
	public Person apply(PersonDto source) {
		if (source == null) {
			return null;
		}
		Person result = domainObjectsFactory.createPerson();
		map(source::getFirstName, result::setFirstName);
		map(source::getLastName, result::setLastName);
		map(source::getBirthDate, result::setBirthDate, localDateToDate);
		map(source::getGender, result::setGender);
		return result;
	}
}
