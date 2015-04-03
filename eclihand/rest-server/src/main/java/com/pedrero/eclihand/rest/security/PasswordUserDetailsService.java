package com.pedrero.eclihand.rest.security;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.service.biz.UserService;

@Controller(value = "passwordUserDetailsService")
public class PasswordUserDetailsService extends AbstractUserDetailsService {

	@Resource
	private UserService userService;

	@Override
	protected String retrieveSecretSignKeyForUser(String login) throws UsernameNotFoundException {
		return userService.retrievePasswordByLogin(login);
	}

}
