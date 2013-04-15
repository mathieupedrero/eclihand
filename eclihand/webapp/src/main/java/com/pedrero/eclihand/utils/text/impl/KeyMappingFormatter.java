package com.pedrero.eclihand.utils.text.impl;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import com.pedrero.eclihand.utils.text.MessageResolver;

public class KeyMappingFormatter<T extends Object> extends EclihandUiConverter<String, T> {
	private final Class<String> PRESENTATION_TYPE = String.class;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2810340127901179457L;

	@Resource
	private MessageResolver messageResolver;

	private Map<Object, String> keyMapping;

	private Class<T> modelType;

	public Map<Object, String> getKeyMapping() {
		return keyMapping;
	}

	public void setKeyMapping(Map<Object, String> keysMapping) {
		this.keyMapping = keysMapping;
	}

	@Override
	public Class<T> getModelType() {
		return modelType;
	}

	/**
	 * @param modelType
	 *            the modelType to set
	 */
	protected void setModelType(Class<T> modelType) {
		this.modelType = modelType;
	}

	@Override
	public Class<String> getPresentationType() {
		return PRESENTATION_TYPE;
	}

	@Override
	protected T doConvertToModel(String value, Locale locale) {
		throw new RuntimeException("Converting " + getPresentationType() + " to " + getModelType() + "is not yet implemented");
	}

	@Override
	protected String doConvertToPresentation(T value, Locale locale) {
		return messageResolver.getMessage(keyMapping.get(value));
	}

}
