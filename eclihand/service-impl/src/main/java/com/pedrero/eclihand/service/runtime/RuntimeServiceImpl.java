package com.pedrero.eclihand.service.runtime;

import java.time.Clock;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.model.exception.EclihandMessage;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

@Service
@Transactional
public class RuntimeServiceImpl implements RuntimeService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RuntimeServiceImpl.class);

	private final Map<String, Session> RUNTIME_SESSIONS = new HashMap<String, RuntimeServiceImpl.Session>();

	private static final Long TIME_SHIFT_TOLERANCE_MILLIS = 600000l;
	private static final Long TOKEN_LIFE_DURATION = 1200000l;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#
	 * createNewSessionForUser(java.lang.String, java.util.Date)
	 */
	@Override
	public String createNewSessionForUser(String login,
			ZonedDateTime clientTimeRequestDate)
			throws TimeConsistencyException {
		ZonedDateTime serverTime = giveServerTime();
		Duration timeShift = Duration
				.between(clientTimeRequestDate, serverTime);
		if (timeShift.toMillis() > TIME_SHIFT_TOLERANCE_MILLIS) {
			LOGGER.error(
					"Timeshift between client time and server time is too big ({} ms)",
					clientTimeRequestDate);
			throw new TimeConsistencyException(new EclihandMessage(
					"error.time_shift_too_large"));
		}
		Session newSession = new Session(serverTime, clientTimeRequestDate,
				computeTokenFor());
		RUNTIME_SESSIONS.put(login, newSession);
		return newSession.securityToken;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.service.impl.runtime.RuntimeService#findTokenFor
	 * (java.lang.String)
	 */
	@Override
	public String findTokenFor(String login) throws NoCurrentSessionException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session exists for user [{}]", login);
			return RUNTIME_SESSIONS.get(login).securityToken;
		}
		throw new NoCurrentSessionException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#
	 * findTokenCheckingTimeConsistencyFor(java.lang.String, java.util.Date)
	 */
	@Override
	public String findTokenCheckingTimeConsistencyFor(String login,
			ZonedDateTime clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session exists for user [{}]", login);
			Session currentSession = RUNTIME_SESSIONS.get(login);
			checkClientDateYoungerThanLastSessionActivity(
					clientTimeRequestDate, currentSession);
			return currentSession.securityToken;
		}
		throw new NoCurrentSessionException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#
	 * checkRequestTimeConsistencyForUser(java.lang.String, java.util.Date)
	 */
	@Override
	public void checkRequestTimeConsistencyForUser(String login,
			ZonedDateTime clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session exists for user [{}]", login);
			Session currentSession = RUNTIME_SESSIONS.get(login);
			checkClientDateYoungerThanLastSessionActivity(
					clientTimeRequestDate, currentSession);
		}
		throw new NoCurrentSessionException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.service.impl.runtime.RuntimeService#giveServerTime()
	 */
	@Override
	public ZonedDateTime giveServerTime() {
		return Clock.systemUTC().instant().atZone(Clock.systemUTC().getZone());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.service.impl.runtime.RuntimeService#cleanSessions()
	 */
	@Override
	@Scheduled(fixedRate = 10000)
	public void cleanSessions() {
		LOGGER.debug("{} - Going to clean client sessions", this.hashCode());
		if (!RUNTIME_SESSIONS.isEmpty()) {
			final ZonedDateTime serverTime = giveServerTime();
			RUNTIME_SESSIONS
					.entrySet()
					.removeIf(
							entry -> (Duration.between(
									entry.getValue().lastActivityServerDate,
									serverTime).toMillis() < TOKEN_LIFE_DURATION));
		}
	}

	private String computeTokenFor() {
		return UUID.randomUUID().toString();
	}

	private void checkClientDateYoungerThanLastSessionActivity(
			ZonedDateTime clientTimeRequestDate, Session currentSession)
			throws TimeConsistencyException {
		if (!clientTimeRequestDate
				.isAfter(currentSession.lastActivityClientDate)) {
			LOGGER.error(
					"New client request is older ({}) than the latest executed query ({})",
					clientTimeRequestDate,
					currentSession.lastActivityClientDate);
			throw new TimeConsistencyException(new EclihandMessage(
					"error.new_request_is_older_than_previous_one"));
		}
	}

	class Session {
		private final ZonedDateTime lastActivityServerDate;
		private final ZonedDateTime lastActivityClientDate;
		private final String securityToken;

		public Session(ZonedDateTime creationServerDate,
				ZonedDateTime lastActivityClientDate, String securityToken) {
			super();
			this.lastActivityServerDate = creationServerDate;
			this.lastActivityClientDate = lastActivityClientDate;
			this.securityToken = securityToken;
		}
	}

}
