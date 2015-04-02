package com.pedrero.eclihand.service.biz.transerval;

import java.util.Date;

import com.pedrero.eclihand.service.runtime.exception.BadCredentialsException;
import com.pedrero.eclihand.service.runtime.exception.EclihandAuthenticationException;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

public interface AuthenticationService {

	void checkRequestTimeConsistencyForUser(String login, Date clientTimeRequestDate)
			throws EclihandAuthenticationException;

	/**
	 * Retrieves the current secret token associated to an user session
	 * 
	 * @param login
	 *            the login of the user to find session
	 * @return the secret token
	 * @throws NoCurrentSessionException
	 *             If no session exists for this user.
	 */
	String findTokenFor(String login) throws NoCurrentSessionException;

	/**
	 * checks the submitted credentials with the registered eclihand users.<br/>
	 * If needed, creates a session, and returns the secret token associated
	 * with the user session.
	 * 
	 * @param login
	 * @param password
	 * @return the secret token associated with the authenticated user.
	 * @throws BadCredentialsException
	 */
	String authenticate(String login, String password, Date clientTimeRequestDate) throws BadCredentialsException,
			TimeConsistencyException;

}
