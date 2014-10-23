package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.AuthorizationDto;
import com.pedrero.eclihand.service.AuthorizationService;

@RestController
@RequestMapping(consumes = "application/json", produces = "application/json", value = "/authorization")
public class AuthorizationWs extends AbstractWs<AuthorizationDto> {

	@Resource
	private AuthorizationService authorizationService;

	@Override
	protected AuthorizationService getService() {
		return authorizationService;
	}

}
