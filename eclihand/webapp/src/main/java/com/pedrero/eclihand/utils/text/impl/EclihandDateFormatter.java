package com.pedrero.eclihand.utils.text.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.LocaleContainer;

@Component
public class EclihandDateFormatter extends EclihandUiConverter<String, Date> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4158373783006365369L;

	private final Class<String> PRESENTATION_TYPE = String.class;
	private final Class<Date> MODEL_TYPE = Date.class;

	@Resource
	private LocaleContainer localeContainer;

	@Override
	public Class<Date> getModelType() {
		return MODEL_TYPE;
	}

	@Override
	public Class<String> getPresentationType() {
		return PRESENTATION_TYPE;
	}

	@Override
	protected Date doConvertToModel(String value,
			Class<? extends Date> targetType, Locale locale)
			throws ParseException {
		DateFormatter form = new DateFormatter();
		return form.parse(value, locale);
	}

	@Override
	protected String doConvertToPresentation(Date value,
			Class<? extends String> targetType, Locale locale) {
		DateFormatter form = new DateFormatter();
		return form.print(value, locale);
	}

}
