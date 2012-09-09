package com.pedrero.eclihand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.PersonConverter;
import com.pedrero.eclihand.dao.PersonDao;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.dto.PersonDto;
import com.pedrero.eclihand.service.PersonService;

@SuppressWarnings("rawtypes")
@Service
public class PersonServiceImpl extends
 DataObjectServiceImpl<PersonDto, Person>
		implements PersonService {
	@Resource
	private PersonConverter personConverter;

	@Resource
	private PersonDao personDao;

	@Override
	public PersonConverter getConverter() {
		return personConverter;
	}

	public void setPersonConverter(PersonConverter personConverter) {
		this.personConverter = personConverter;
	}

	@Override
	public PersonDao getDao() {
	return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
