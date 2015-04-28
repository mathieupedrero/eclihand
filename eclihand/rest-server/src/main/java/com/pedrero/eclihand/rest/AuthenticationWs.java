package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.biz.transerval.AuthenticationService;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationWs extends AbstractWs {

	@Resource
	private AuthenticationService authenticationService;

	@RequestMapping(produces = IRestWebService.APPLICATION_JSON, method = RequestMethod.GET, value = "/touch")
	public UserDto touchAndRetrieveToken() {
		return authenticationService.giveAuthenticatedUser();
	}
}
