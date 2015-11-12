package com.pedrero.eclihand.service.runtime.exception;

import com.pedrero.eclihand.model.exception.EclihandMessage;

public class TimeConsistencyException extends EclihandAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6089059097328328127L;

	public TimeConsistencyException() {
	}

	public TimeConsistencyException(EclihandMessage message, Throwable cause) {
		super(message, cause);
	}

	public TimeConsistencyException(EclihandMessage message) {
		super(message);
	}

	public TimeConsistencyException(Throwable cause) {
		super(cause);
	}

}
