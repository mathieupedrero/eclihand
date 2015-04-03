package com.pedrero.eclihand.rest.security;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.service.biz.transerval.AuthenticationService;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;

@Controller(value = "sessionTokenUserDetailsService")
public class SessionTokenUserDetailsService extends AbstractUserDetailsService {

	@Resource
	private AuthenticationService authenticationService;

	@Override
	protected String retrieveSecretSignKeyForUser(String login) {
		try {
			return authenticationService.findTokenFor(login);
		} catch (NoCurrentSessionException e) {
			throw new UsernameNotFoundException("No session exists for user", e);
		}
	}

}
