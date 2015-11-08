package com.pedrero.eclihand.service.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.Converter;
import com.pedrero.eclihand.converter.in.PersonDtoToPerson;
import com.pedrero.eclihand.converter.out.PersonToPersonDto;
import com.pedrero.eclihand.dao.PersonDao;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.dto.PersonDto;
import com.pedrero.eclihand.service.biz.PersonService;

@Service
public class PersonServiceImpl extends DataObjectServiceImpl<PersonDto, Person> implements PersonService {
	@Resource
	private PersonToPersonDto outPersonConverter;

	@Resource
	private PersonDtoToPerson inPersonConverter;

	@Resource
	private PersonDao personDao;

	@Override
	public PersonToPersonDto getOutConverter() {
		return outPersonConverter;
	}

	@Override
	public PersonDao getDao() {
		return personDao;
	}

	@Override
	public Converter<PersonDto, Person> getInConverter() {
		return inPersonConverter;
	}

}
