package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.PersonDto;
import com.pedrero.eclihand.service.PersonService;

@RestController
@RequestMapping(consumes = "application/json", produces = "application/json", value = "/person")
public class PersonWs extends AbstractWs<PersonDto> {

	@Resource
	private PersonService personService;

	@Override
	protected PersonService getService() {
		return personService;
	}

}
