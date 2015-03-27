package com.pedrero.eclihand.service.impl.runtime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.service.runtime.IRuntimeService;
import com.pedrero.eclihand.service.runtime.TimeConsistencyException;

@Service
@Transactional
public class RuntimeServiceImpl implements IRuntimeService {
	private final static Logger LOGGER = LoggerFactory.getLogger(RuntimeServiceImpl.class);

	private final Map<String, Session> RUNTIME_SESSIONS = new HashMap<String, RuntimeServiceImpl.Session>();

	@Override
	public String registerClientRequest(String login, Date clientTimeRequestDate) throws TimeConsistencyException {
		if (RUNTIME_SESSIONS.containsKey(login)) {
			LOGGER.debug("A session allready exists for user [{}]", login);
			Session currentSession = RUNTIME_SESSIONS.get(login);
			if (!clientTimeRequestDate.after(currentSession.lastActivityClientDate)) {
				LOGGER.error("New client request is older ({}) than the latest executed query ({})",
						clientTimeRequestDate, currentSession.lastActivityClientDate);
				throw new TimeConsistencyException("New client request is older than the latest executed query");
			}
			currentSession.lastActivityClientDate = clientTimeRequestDate;
			currentSession.lastActivityServerDate = giveServerTime();
			return currentSession.securityToken;
		}
		Date serverTime = giveServerTime();
		Session newSession = new Session(serverTime, clientTimeRequestDate, computeTokenFor());
		RUNTIME_SESSIONS.put(login, newSession);
		return newSession.securityToken;
	}

	@Override
	public Date giveServerTime() {
		return new Date();
	}

	private String computeTokenFor() {
		return UUID.randomUUID().toString();
	}

	class Session {
		private Date lastActivityServerDate;
		private Date lastActivityClientDate;
		private final String securityToken;

		public Session(Date lastActivityServerDate, Date lastActivityClientDate, String securityToken) {
			super();
			this.lastActivityServerDate = lastActivityServerDate;
			this.lastActivityClientDate = lastActivityClientDate;
			this.securityToken = securityToken;
		}
	}

}
