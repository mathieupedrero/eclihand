package com.pedrero.eclihand.rest.security;

import java.time.ZonedDateTime;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.service.biz.transerval.AuthenticationService;
import com.pedrero.eclihand.service.runtime.exception.EclihandAuthenticationException;

@Controller
public class SessionTokenAuthenticationProvider extends EclihandAuthenticationProvider {

	@Resource
	private AuthenticationService authenticationService;

	@Override
	protected String findSessionTokenFor(UserDetails userDetails, ZonedDateTime clientTimeRequest)
			throws EclihandAuthenticationException {
		return userDetails.getPassword();
	}

}
