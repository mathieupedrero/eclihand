package com.pedrero.eclihand.utils.text.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pedrero.eclihand.utils.text.UriFragmentManager;

public class UriFragmentManagerImplTest {

	private UriFragmentManager fragmentManager;

	@Before
	public void setUp() throws Exception {
		fragmentManager = new UriFragmentManagerImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void parse_nominal() {
		String toParse = "toto=value";
		Map<String,String> result = fragmentManager.parse(toParse);
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.containsKey("toto"));
		assertEquals("value", result.get("toto"));
	}

	@Test
	public void parse_manyProperties() {
		String toParse = "toto=value;titi=valeur";
		Map<String, String> result = fragmentManager.parse(toParse);

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.containsKey("toto"));
		assertEquals("value", result.get("toto"));
		assertTrue(result.containsKey("titi"));
		assertEquals("valeur", result.get("titi"));
	}

	@Test
	public void parse_escapedSemiColon() {
		String toParse = "toto=v/;alue";
		Map<String, String> result = fragmentManager.parse(toParse);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.containsKey("toto"));
		assertEquals("v;alue", result.get("toto"));
	}

	@Test
	public void parse_escapedSlash() {
		String toParse = "toto=v//alue";
		Map<String, String> result = fragmentManager.parse(toParse);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.containsKey("toto"));
		assertEquals("v/alue", result.get("toto"));
	}

	@Test
	public void parse_escapedEquals() {
		String toParse = "toto=v/=alue";
		Map<String, String> result = fragmentManager.parse(toParse);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.containsKey("toto"));
		assertEquals("v=alue", result.get("toto"));
	}

}
