package com.pedrero.eclihand.service.runtime;

import java.util.Date;

public interface IRuntimeService {
	/**
	 * @param login
	 *            The login to register request for
	 * @param clientTimeRequestDate
	 *            time on client side given by the http request
	 * @return
	 * @throws TimeConsistencyException
	 */
	String registerClientRequest(String login, Date clientTimeRequestDate)
			throws TimeConsistencyException;

	/**
	 * @return The server time
	 */
	Date giveServerTime();

	/**
	 * cleans old session tokens
	 */
	void cleanTokens();
}
