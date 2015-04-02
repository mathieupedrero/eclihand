package com.pedrero.eclihand.service.runtime.exception;

public class BadCredentialsException extends EclihandAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4605524878363249575L;

	public BadCredentialsException() {
	}

	public BadCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadCredentialsException(String message) {
		super(message);
	}

	public BadCredentialsException(Throwable cause) {
		super(cause);
	}

}
