package com.pedrero.eclihand.utils.text.impl;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public abstract class EclihandUiConverter<PRESENTATION, MODEL> implements Converter<PRESENTATION, MODEL> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8230105160760079866L;

	public EclihandUiConverter() {
		super();
	}

	@Override
	public final MODEL convertToModel(PRESENTATION value, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		try {
			return doConvertToModel(value, locale);
		} catch (Exception e) {
			throw handleExceptionOnConversion(value, getModelType(), e);
		}
	}

	@Override
	public final PRESENTATION convertToPresentation(MODEL value, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		try {
			return doConvertToPresentation(value, locale);
		} catch (Exception e) {
			throw handleExceptionOnConversion(value, getPresentationType(), e);
		}
	}

	abstract protected MODEL doConvertToModel(PRESENTATION value, Locale locale) throws Exception;

	abstract protected PRESENTATION doConvertToPresentation(MODEL value, Locale locale) throws Exception;

	private com.vaadin.data.util.converter.Converter.ConversionException handleExceptionOnConversion(Object value,
			Class<?> convertToType, Exception e) {
		return new ConversionException("Error while converting " + value + " to " + convertToType.getSimpleName(), e);
	}

}