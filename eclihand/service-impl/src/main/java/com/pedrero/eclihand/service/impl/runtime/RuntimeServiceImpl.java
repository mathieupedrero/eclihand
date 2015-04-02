package com.pedrero.eclihand.service.impl.runtime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.service.runtime.RuntimeService;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

@Service
@Transactional
public class RuntimeServiceImpl implements RuntimeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeServiceImpl.class);

	private final Map<String, Session> RUNTIME_SESSIONS = new HashMap<String, RuntimeServiceImpl.Session>();

	private static final Long TIME_SHIFT_TOLERANCE_MILLIS = 600000l;
	private static final Long TOKEN_LIFE_DURATION = 1200000l;

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#createNewSessionForUser(java.lang.String, java.util.Date)
	 */
	@Override
	public String createNewSessionForUser(String login, Date clientTimeRequestDate) throws TimeConsistencyException {
		Date serverTime = giveServerTime();
		long timeShift = Math.abs(serverTime.getTime() - clientTimeRequestDate.getTime());
		if (timeShift > TIME_SHIFT_TOLERANCE_MILLIS) {
			LOGGER.error("Timeshift between client time and server time is too big ({} ms)", clientTimeRequestDate);
			throw new TimeConsistencyException("Timeshift between client time and server time is too big");
		}
		Session newSession = new Session(serverTime, clientTimeRequestDate, computeTokenFor());
		RUNTIME_SESSIONS.put(login, newSession);
		return newSession.securityToken;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#findTokenFor(java.lang.String)
	 */
	@Override
	public String findTokenFor(String login) throws NoCurrentSessionException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session exists for user [{}]", login);
			return RUNTIME_SESSIONS.get(login).securityToken;
		}
		throw new NoCurrentSessionException();
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#findTokenCheckingTimeConsistencyFor(java.lang.String, java.util.Date)
	 */
	@Override
	public String findTokenCheckingTimeConsistencyFor(String login, Date clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session exists for user [{}]", login);
			Session currentSession = RUNTIME_SESSIONS.get(login);
			checkClientDateYoungerThanLastSessionActivity(clientTimeRequestDate, currentSession);
			return currentSession.securityToken;
		}
		throw new NoCurrentSessionException();
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#checkRequestTimeConsistencyForUser(java.lang.String, java.util.Date)
	 */
	@Override
	public void checkRequestTimeConsistencyForUser(String login, Date clientTimeRequestDate)
			throws NoCurrentSessionException, TimeConsistencyException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session exists for user [{}]", login);
			Session currentSession = RUNTIME_SESSIONS.get(login);
			checkClientDateYoungerThanLastSessionActivity(clientTimeRequestDate, currentSession);
		}
		throw new NoCurrentSessionException();
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#giveServerTime()
	 */
	@Override
	public Date giveServerTime() {
		return new Date();
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.service.impl.runtime.RuntimeService#cleanSessions()
	 */
	@Override
	@Scheduled(fixedRate = 10000)
	public void cleanSessions() {
		LOGGER.debug("{} - Going to clean client sessions", this.hashCode());
		if (!RUNTIME_SESSIONS.isEmpty()) {
			final Date serverTime = giveServerTime();
			RUNTIME_SESSIONS
					.entrySet()
					.removeIf(
							entry -> (serverTime.getTime() - entry.getValue().lastActivityServerDate.getTime() < TOKEN_LIFE_DURATION));
		}
	}

	private String computeTokenFor() {
		return UUID.randomUUID().toString();
	}

	private void checkClientDateYoungerThanLastSessionActivity(Date clientTimeRequestDate, Session currentSession)
			throws TimeConsistencyException {
		if (!clientTimeRequestDate.after(currentSession.lastActivityClientDate)) {
			LOGGER.error("New client request is older ({}) than the latest executed query ({})", clientTimeRequestDate,
					currentSession.lastActivityClientDate);
			throw new TimeConsistencyException("New client request is older than the latest executed query");
		}
	}

	class Session {
		private final Date lastActivityServerDate;
		private final Date lastActivityClientDate;
		private final String securityToken;

		public Session(Date creationServerDate, Date lastActivityClientDate, String securityToken) {
			super();
			this.lastActivityServerDate = creationServerDate;
			this.lastActivityClientDate = lastActivityClientDate;
			this.securityToken = securityToken;
		}
	}

}
