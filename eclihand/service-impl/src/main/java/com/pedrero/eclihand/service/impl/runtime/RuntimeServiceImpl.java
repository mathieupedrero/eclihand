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

import com.pedrero.eclihand.service.runtime.IRuntimeService;
import com.pedrero.eclihand.service.runtime.TimeConsistencyException;

@Service
@Transactional
public class RuntimeServiceImpl implements IRuntimeService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RuntimeServiceImpl.class);

	private final Map<String, Session> RUNTIME_SESSIONS = new HashMap<String, RuntimeServiceImpl.Session>();

	private static final Long TIME_SHIFT_TOLERANCE_MILLIS = 600000l;
	private static final Long TOKEN_LIFE_DURATION = 1200000l;

	@Override
	public String registerClientRequest(String login, Date clientTimeRequestDate)
			throws TimeConsistencyException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session allready exists for user [{}]", login);
			Session currentSession = RUNTIME_SESSIONS.get(login);
			if (!clientTimeRequestDate
					.after(currentSession.lastActivityClientDate)) {
				LOGGER.error(
						"New client request is older ({}) than the latest executed query ({})",
						clientTimeRequestDate,
						currentSession.lastActivityClientDate);
				throw new TimeConsistencyException(
						"New client request is older than the latest executed query");
			}
			currentSession.lastActivityClientDate = clientTimeRequestDate;
			currentSession.lastActivityServerDate = giveServerTime();
			return currentSession.securityToken;
		}
		Date serverTime = giveServerTime();
		long timeShift = Math.abs(serverTime.getTime()
				- clientTimeRequestDate.getTime());
		if (timeShift > TIME_SHIFT_TOLERANCE_MILLIS) {
			LOGGER.error(
					"Timeshift between client time and server time is too big ({} ms)",
					clientTimeRequestDate);
			throw new TimeConsistencyException(
					"Timeshift between client time and server time is too big");
		}
		Session newSession = new Session(serverTime, clientTimeRequestDate,
				computeTokenFor());
		RUNTIME_SESSIONS.put(login, newSession);
		return newSession.securityToken;
	}

	@Override
	public Date giveServerTime() {
		return new Date();
	}

	@Override
	@Scheduled(fixedRate = 10000)
	public void cleanTokens() {
		LOGGER.debug("{} - Going to clean client sessions", this.hashCode());
		if (!RUNTIME_SESSIONS.isEmpty()) {
			final Date serverTime = giveServerTime();
			RUNTIME_SESSIONS.entrySet()
					.removeIf(
							entry -> (serverTime.getTime()
									- entry.getValue().lastActivityServerDate
											.getTime() < TOKEN_LIFE_DURATION));
		}
	}

	private String computeTokenFor() {
		return UUID.randomUUID().toString();
	}

	class Session {
		private Date lastActivityServerDate;
		private Date lastActivityClientDate;
		private final String securityToken;

		public Session(Date creationServerDate, Date lastActivityClientDate,
				String securityToken) {
			super();
			this.lastActivityServerDate = creationServerDate;
			this.lastActivityClientDate = lastActivityClientDate;
			this.securityToken = securityToken;
		}
	}

}
