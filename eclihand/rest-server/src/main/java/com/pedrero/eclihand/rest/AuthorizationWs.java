package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.AuthorizationDto;
import com.pedrero.eclihand.service.biz.AuthorizationService;

@RestController
@RequestMapping(consumes = IRestWebService.APPLICATION_JSON, produces = IRestWebService.APPLICATION_JSON, value = "/authorization")
public class AuthorizationWs extends AbstractEntityWs<AuthorizationDto> {

	@Resource
	private AuthorizationService authorizationService;

	@Override
	protected AuthorizationService getService() {
		return authorizationService;
	}

}
