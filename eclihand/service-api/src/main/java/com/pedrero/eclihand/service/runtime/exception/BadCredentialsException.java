package com.pedrero.eclihand.service.runtime.exception;

import com.pedrero.eclihand.model.exception.EclihandMessage;

public class BadCredentialsException extends EclihandAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4605524878363249575L;

	public BadCredentialsException() {
	}

	public BadCredentialsException(EclihandMessage message, Throwable cause) {
		super(message, cause);
	}

	public BadCredentialsException(EclihandMessage message) {
		super(message);
	}

	public BadCredentialsException(Throwable cause) {
		super(cause);
	}

}
