package com.pedrero.eclihand.utils.text.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.pedrero.eclihand.utils.text.Formatter;
import com.pedrero.eclihand.utils.text.MessageResolver;

public class KeyMappingFormatter implements Formatter {
	@Resource
	private MessageResolver messageResolver;

	private Map<Object, String> keyMapping;

	@Override
	public String format(Object object) {
		return messageResolver.getMessage(keyMapping.get(object));
	}

	public Map<Object, String> getKeyMapping() {
		return keyMapping;
	}

	public void setKeyMapping(Map<Object, String> keysMapping) {
		this.keyMapping = keysMapping;
	}

}
