package com.pedrero.eclihand.model.exception;

import static com.pedrero.eclihand.model.exception.EclihandMessageParam.buildParamFor;

import java.util.ArrayList;
import java.util.List;

public class EclihandMessage {
	private final String key;
	private final List<EclihandMessageParam> params;

	public EclihandMessage(String key) {
		super();
		this.key = key;
		this.params = new ArrayList<EclihandMessageParam>();
	}

	public EclihandMessage(String key, Object... params) {
		super();
		this.key = key;
		this.params = new ArrayList<EclihandMessageParam>();
		for (Object param : params) {
			this.params.add(buildParamFor(param));
		}
	}

	public String getKey() {
		return key;
	}

	public List<EclihandMessageParam> getParams() {
		return params;
	}

}
