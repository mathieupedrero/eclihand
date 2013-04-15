package com.pedrero.eclihand.utils.text.impl;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.data.util.converter.Converter.ConversionException;

public class SeasonFormatterTest {

	private SeasonFormatter seasonFormatter;

	@Before
	public void setUp() throws Exception {
		seasonFormatter = new SeasonFormatter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void convertToModel() {
		int actual = seasonFormatter.convertToModel("2010/2011", Locale.FRANCE);
		assertEquals(2011, actual);
	}

	@Test(expected = ConversionException.class)
	public void convertToModel_badFormat() {
		seasonFormatter.convertToModel("toto", Locale.FRANCE);
	}

	@Test
	public void convertToPresentation() {
		String actual = seasonFormatter.convertToPresentation(2011, Locale.FRANCE);
		assertEquals("2010/2011", actual);
	}

}
