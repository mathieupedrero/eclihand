package com.pedrero.eclihand.rest.security;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.service.biz.UserService;

@Controller(value = "passwordUserDetailsService")
public class PasswordUserDetailsService extends AbstractUserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUserDetailsService.class);

	@Resource
	private UserService userService;

	@Override
	protected String retrieveSecretSignKeyForUser(String login) throws UsernameNotFoundException {
		LOGGER.info("Going to retrieve password for user {}", login);
		return userService.retrievePasswordByLogin(login);
	}

}
