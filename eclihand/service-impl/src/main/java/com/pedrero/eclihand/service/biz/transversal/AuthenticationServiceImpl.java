package com.pedrero.eclihand.service.biz.transversal;

import java.time.ZonedDateTime;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.biz.UserService;
import com.pedrero.eclihand.service.biz.transerval.AuthenticationContext;
import com.pedrero.eclihand.service.biz.transerval.AuthenticationService;
import com.pedrero.eclihand.service.runtime.RuntimeService;
import com.pedrero.eclihand.service.runtime.exception.BadCredentialsException;
import com.pedrero.eclihand.service.runtime.exception.EclihandAuthenticationException;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Resource
	private UserService userService;

	@Resource
	private AuthenticationContext authenticationContext;

	@Resource
	private RuntimeService runtimeService;

	@Override
	public void checkRequestTimeConsistencyForUser(String login, ZonedDateTime clientTimeRequestDate)
			throws EclihandAuthenticationException {
		runtimeService.checkRequestTimeConsistencyForUser(login, clientTimeRequestDate);
	}

	@Override
	public String findTokenFor(String login) throws NoCurrentSessionException {
		return runtimeService.findTokenFor(login);
	}

	@Override
	public String authenticate(String login, String password, ZonedDateTime clientTimeRequestDate)
			throws BadCredentialsException, TimeConsistencyException {
		LOGGER.info("User [{}] tries to log in", login);
		userService.checkCredentials(login, password);
		try {
			return runtimeService.findTokenCheckingTimeConsistencyFor(login, clientTimeRequestDate);
		} catch (NoCurrentSessionException e) {
			return runtimeService.createNewSessionForUser(login, clientTimeRequestDate);
		}
	}

	@Override
	public UserDto giveAuthenticatedUser() {
		return userService.retrieveByLogin(authenticationContext.giveUsername());
	}
}
