package com.pedrero.eclihand.service.runtime;

import java.util.Date;

import com.pedrero.eclihand.service.runtime.exception.EclihandAuthenticationException;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

public interface RuntimeService {
	/**
	 * @param login
	 *            The login to register request for
	 * @param clientTimeRequestDate
	 *            time on client side given by the http request
	 * @return
	 * @throws TimeConsistencyException
	 */
	String createNewSessionForUser(String login, Date clientTimeRequestDate) throws TimeConsistencyException;

	String findTokenFor(String login) throws NoCurrentSessionException;

	void checkRequestTimeConsistencyForUser(String login, Date clientTimeRequestDate)
			throws EclihandAuthenticationException;

	/**
	 * @return The server time
	 */
	Date giveServerTime();

	/**
	 * cleans old session tokens
	 */
	void cleanTokens();
}
