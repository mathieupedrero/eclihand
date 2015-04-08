package com.pedrero.eclihand.service.runtime;

import java.util.Date;

import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

public interface RuntimeService {

	public abstract String createNewSessionForUser(String login, Date clientTimeRequestDate)
			throws TimeConsistencyException;

	public abstract String findTokenFor(String login) throws NoCurrentSessionException;

	public abstract String findTokenCheckingTimeConsistencyFor(String login, Date clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException;

	public abstract void checkRequestTimeConsistencyForUser(String login, Date clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException;

	public abstract Date giveServerTime();

	public abstract void cleanSessions();

}