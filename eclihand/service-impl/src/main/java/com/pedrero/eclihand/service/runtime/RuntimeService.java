package com.pedrero.eclihand.service.runtime;

import java.time.ZonedDateTime;

import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

public interface RuntimeService {

	public abstract String createNewSessionForUser(String login, ZonedDateTime clientTimeRequestDate)
			throws TimeConsistencyException;

	public abstract String findTokenFor(String login) throws NoCurrentSessionException;

	public abstract String findTokenCheckingTimeConsistencyFor(String login, ZonedDateTime clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException;

	public abstract void checkRequestTimeConsistencyForUser(String login, ZonedDateTime clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException;

	public abstract ZonedDateTime giveServerTime();

	public abstract void cleanSessions();

}