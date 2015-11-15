package com.pedrero.eclihand.model.exception;

import static com.pedrero.eclihand.model.exception.EclihandMessageParam.buildParamFor;

import java.util.HashMap;
import java.util.Map;

public class EclihandMessage {

	public static NamedParam param(String name, Object param) {
		return new NamedParam(name, param);
	}

	private final String key;
	private final Map<String, EclihandMessageParam> paramsByName;

	public EclihandMessage(String key) {
		super();
		this.key = key;
		this.paramsByName = new HashMap<String, EclihandMessageParam>();
	}

	public EclihandMessage(String key, NamedParam... namedParams) {
		super();
		this.key = key;
		this.paramsByName = new HashMap<String, EclihandMessageParam>();
		for (NamedParam namedParam : namedParams) {
			this.paramsByName.put(namedParam.name,
					buildParamFor(namedParam.param));
		}
	}

	public String getKey() {
		return key;
	}

	public Map<String, EclihandMessageParam> getParamsByName() {
		return paramsByName;
	}

	private static class NamedParam {
		final String name;
		final Object param;

		public NamedParam(String name, Object param) {
			super();
			this.name = name;
			this.param = param;
		}
	}

}
