package com.pedrero.eclihand.model.exception;

import java.util.Collection;
import java.util.stream.Collectors;

public class EclihandMessageParam {

	static EclihandMessageParam buildParamFor(Object param) {
		if (param instanceof EclihandMessage) {
			return new EclihandMessageParam((EclihandMessage) param);
		}
		if (param instanceof Collection<?>) {
			return new EclihandMessageParam(((Collection<?>) param).stream()
					.map(o -> buildParamFor(o)).collect(Collectors.toList()));
		}
		return new EclihandMessageParam(param);
	}

	private final Object param;
	private final EclihandMessage nestedMessage;

	private EclihandMessageParam(EclihandMessage nestedMessage) {
		super();
		this.param = null;
		this.nestedMessage = nestedMessage;
	}

	private EclihandMessageParam(Object param) {
		super();
		this.param = param;
		this.nestedMessage = null;
	}

	public Object getParam() {
		return param;
	}

	public EclihandMessage getNestedMessage() {
		return nestedMessage;
	}

}
