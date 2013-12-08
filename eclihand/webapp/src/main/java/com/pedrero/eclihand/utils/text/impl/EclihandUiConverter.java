package com.pedrero.eclihand.utils.text.impl;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public abstract class EclihandUiConverter<PRESENTATION, MODEL> implements
		Converter<PRESENTATION, MODEL> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8230105160760079866L;

	public EclihandUiConverter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.util.converter.Converter#convertToModel(java.lang.Object,
	 * java.lang.Class, java.util.Locale)
	 */
	@Override
	public MODEL convertToModel(PRESENTATION value,
			Class<? extends MODEL> targetType, Locale locale)
			throws ConversionException {
		try {
			return doConvertToModel(value, targetType, locale);
		} catch (Exception e) {
			throw handleExceptionOnConversion(value, getModelType(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.util.converter.Converter#convertToPresentation(java.lang
	 * .Object, java.lang.Class, java.util.Locale)
	 */
	@Override
	public PRESENTATION convertToPresentation(MODEL value,
			Class<? extends PRESENTATION> targetType, Locale locale)
			throws ConversionException {
		try {
			return doConvertToPresentation(value, targetType, locale);
		} catch (Exception e) {
			throw handleExceptionOnConversion(value, getPresentationType(), e);
		}
	}

	abstract protected MODEL doConvertToModel(PRESENTATION value,
			Class<? extends MODEL> targetType, Locale locale) throws Exception;

	abstract protected PRESENTATION doConvertToPresentation(MODEL value,
			Class<? extends PRESENTATION> targetType, Locale locale)
			throws Exception;

	private com.vaadin.data.util.converter.Converter.ConversionException handleExceptionOnConversion(
			Object value, Class<?> convertToType, Exception e) {
		return new ConversionException("Error while converting " + value
				+ " to " + convertToType.getSimpleName(), e);
	}

}