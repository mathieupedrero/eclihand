package com.pedrero.eclihand.service.impl.runtime;

import static org.junit.Assert.fail;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.pedrero.eclihand.service.runtime.TimeConsistencyException;

public class RuntimeServiceImplTest {

	private final RuntimeServiceImpl RUNTIME_SERVICE = new RuntimeServiceImpl();

	@Test
	public void serverTimeConsistencyTest() {
		Date firstDate = new Date();
		Date serverTime = RUNTIME_SERVICE.giveServerTime();
		Date secondDate = new Date();

		if (serverTime.before(firstDate) || secondDate.before(serverTime)) {
			fail();
		}
	}

	@Test(expected = TimeConsistencyException.class)
	public void registerSameDateRequestTest() throws TimeConsistencyException {
		String login = "registerSameDateRequestTest";
		Date sameDate = new Date();
		RUNTIME_SERVICE.registerClientRequest(login, sameDate);
		RUNTIME_SERVICE.registerClientRequest(login, sameDate);
	}

	@Test(expected = TimeConsistencyException.class)
	public void registerOlderRequestTest() throws TimeConsistencyException {
		String login = "registerOlderRequestTest";
		Date firstDate = new Date(10l);
		Date secondDate = new Date(20l);
		RUNTIME_SERVICE.registerClientRequest(login, secondDate);
		RUNTIME_SERVICE.registerClientRequest(login, firstDate);
	}

	@Test()
	public void registerClientRequestTest() throws TimeConsistencyException {
		String login = "registerOlderRequestTest";
		Date firstDate = new Date(10l);
		Date secondDate = new Date(20l);
		Assert.assertEquals("doesn't return same token", RUNTIME_SERVICE.registerClientRequest(login, firstDate),
				RUNTIME_SERVICE.registerClientRequest(login, secondDate));
	}

}
