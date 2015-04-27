package com.pedrero.eclihand.rest.security;

import java.time.ZonedDateTime;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.service.biz.transerval.AuthenticationService;
import com.pedrero.eclihand.service.runtime.exception.EclihandAuthenticationException;

@Controller
public class PasswordAuthenticationProvider extends EclihandAuthenticationProvider {

	@Resource
	private AuthenticationService authenticationService;

	@Override
	protected String findSessionTokenFor(UserDetails userDetails, ZonedDateTime clientTimeRequest)
			throws EclihandAuthenticationException {
		return authenticationService.authenticate(userDetails.getUsername(), userDetails.getPassword(),
				clientTimeRequest);
	}

}
