package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.PersonDto;
import com.pedrero.eclihand.service.biz.PersonService;

@RestController
@RequestMapping(consumes = IRestWebService.APPLICATION_JSON, produces = IRestWebService.APPLICATION_JSON, value = "/person")
public class PersonWs extends AbstractEntityWs<PersonDto> {

	@Resource
	private PersonService personService;

	@Override
	protected PersonService getService() {
		return personService;
	}

}
