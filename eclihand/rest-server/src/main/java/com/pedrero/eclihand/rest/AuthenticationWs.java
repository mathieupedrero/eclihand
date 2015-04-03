package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.service.biz.transerval.AuthenticationService;

@RestController
@RequestMapping(consumes = IRestWebService.APPLICATION_JSON, produces = IRestWebService.APPLICATION_JSON, value = "/authentication")
public class AuthenticationWs extends AbstractWs {

	@Resource
	private AuthenticationService authenticationService;

	@RequestMapping(method = RequestMethod.GET, value = "/touch")
	public String touchAndRetrieveToken() {
		return "OKAY!";
	}
}
