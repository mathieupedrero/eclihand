package com.pedrero.eclihand.utils.text.impl;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.LocaleContainer;

@Component
public class SeasonFormatter extends EclihandUiConverter<String, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4343166612100507086L;

	private final Class<String> PRESENTATION_TYPE = String.class;
	private final Class<Integer> MODEL_TYPE = Integer.class;
	
	@Resource
	private LocaleContainer localeContainer;

	@Override
	public Class<Integer> getModelType() {
		return MODEL_TYPE;
	}

	@Override
	public Class<String> getPresentationType() {
		return PRESENTATION_TYPE;
	}

	@Override
	protected Integer doConvertToModel(String value, Locale locale) {
		Pattern seasonPattern = Pattern.compile("\\d+/(\\d+)");
		Matcher seasonMatcher = seasonPattern.matcher(value);
		if (seasonMatcher.find()) {
			return Integer.parseInt(seasonMatcher.group(1));
		}
		throw new RuntimeException(value + "is not a valid season");
	}

	@Override
	protected String doConvertToPresentation(Integer value, Locale locale) {
		return (value - 1) + "/" + value;
	}

}
