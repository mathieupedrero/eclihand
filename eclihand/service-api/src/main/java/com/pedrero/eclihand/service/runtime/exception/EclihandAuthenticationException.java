package com.pedrero.eclihand.service.runtime.exception;

import com.pedrero.eclihand.service.common.ServiceException;

public class EclihandAuthenticationException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6089059097328328127L;

	public EclihandAuthenticationException() {
	}

	public EclihandAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EclihandAuthenticationException(String message) {
		super(message);
	}

	public EclihandAuthenticationException(Throwable cause) {
		super(cause);
	}

}
