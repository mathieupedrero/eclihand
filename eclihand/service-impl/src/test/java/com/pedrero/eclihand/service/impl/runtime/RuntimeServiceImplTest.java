package com.pedrero.eclihand.service.impl.runtime;

import static org.junit.Assert.fail;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;

import junit.framework.Assert;

import org.junit.Test;

import com.pedrero.eclihand.service.runtime.RuntimeService;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;
import com.pedrero.eclihand.service.runtime.exception.TimeConsistencyException;

public class RuntimeServiceImplTest {

	private final RuntimeService RUNTIME_SERVICE = new RuntimeServiceImpl();

	@Test
	public void serverTimeConsistencyTest() {
		ZonedDateTime firstDate = Clock.systemUTC().instant().atZone(Clock.systemUTC().getZone());
		ZonedDateTime serverTime = RUNTIME_SERVICE.giveServerTime();
		ZonedDateTime secondDate = Clock.systemUTC().instant().atZone(Clock.systemUTC().getZone());

		if (serverTime.isBefore(firstDate) || secondDate.isBefore(serverTime)) {
			fail();
		}
	}

	@Test(expected = TimeConsistencyException.class)
	public void registerSameDateRequestTest() throws TimeConsistencyException, NoCurrentSessionException {
		String login = "registerSameDateRequestTest";
		ZonedDateTime sameDate = Clock.systemUTC().instant().atZone(Clock.systemUTC().getZone());
		RUNTIME_SERVICE.createNewSessionForUser(login, sameDate);
		RUNTIME_SERVICE.checkRequestTimeConsistencyForUser(login, sameDate);
	}

	@Test(expected = TimeConsistencyException.class)
	public void registerOlderRequestTest() throws TimeConsistencyException {
		String login = "registerOlderRequestTest";
		ZonedDateTime firstDate = Instant.ofEpochMilli(10).atZone(Clock.systemUTC().getZone());
		ZonedDateTime secondDate = Instant.ofEpochMilli(20).atZone(Clock.systemUTC().getZone());
		RUNTIME_SERVICE.createNewSessionForUser(login, secondDate);
		RUNTIME_SERVICE.createNewSessionForUser(login, firstDate);
	}

	@Test()
	public void registerClientRequestTestWithTimeCheck() throws TimeConsistencyException, NoCurrentSessionException {
		String login = "registerOlderRequestTest";
		ZonedDateTime secondDate = RUNTIME_SERVICE.giveServerTime();
		ZonedDateTime firstDate = secondDate.minusSeconds(20);
		Assert.assertEquals("doesn't return same token", RUNTIME_SERVICE.createNewSessionForUser(login, firstDate),
				RUNTIME_SERVICE.findTokenCheckingTimeConsistencyFor(login, secondDate));
	}

	@Test()
	public void registerClientRequestTest() throws TimeConsistencyException, NoCurrentSessionException {
		String login = "registerOlderRequestTest";
		ZonedDateTime now = RUNTIME_SERVICE.giveServerTime();
		ZonedDateTime firstDate = now.minusSeconds(20);
		Assert.assertEquals("doesn't return same token", RUNTIME_SERVICE.createNewSessionForUser(login, firstDate),
				RUNTIME_SERVICE.findTokenFor(login));
	}

}
