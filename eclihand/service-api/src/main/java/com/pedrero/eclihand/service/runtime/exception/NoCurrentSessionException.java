package com.pedrero.eclihand.service.runtime.exception;


public class NoCurrentSessionException extends EclihandAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6089059097328328127L;

	public NoCurrentSessionException() {
	}

	public NoCurrentSessionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoCurrentSessionException(String message) {
		super(message);
	}

	public NoCurrentSessionException(Throwable cause) {
		super(cause);
	}

}
