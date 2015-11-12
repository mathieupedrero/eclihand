package com.pedrero.eclihand.service.runtime.exception;

import com.pedrero.eclihand.model.exception.EclihandMessage;

public class NoCurrentSessionException extends EclihandAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6089059097328328127L;

	public NoCurrentSessionException() {
	}

	public NoCurrentSessionException(EclihandMessage message, Throwable cause) {
		super(message, cause);
	}

	public NoCurrentSessionException(EclihandMessage message) {
		super(message);
	}

	public NoCurrentSessionException(Throwable cause) {
		super(cause);
	}

}
