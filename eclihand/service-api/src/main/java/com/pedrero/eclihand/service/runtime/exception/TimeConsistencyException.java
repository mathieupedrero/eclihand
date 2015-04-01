package com.pedrero.eclihand.service.runtime.exception;


public class TimeConsistencyException extends EclihandAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6089059097328328127L;

	public TimeConsistencyException() {
	}

	public TimeConsistencyException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeConsistencyException(String message) {
		super(message);
	}

	public TimeConsistencyException(Throwable cause) {
		super(cause);
	}

}
