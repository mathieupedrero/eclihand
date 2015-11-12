package com.pedrero.eclihand.service.runtime.exception;

import com.pedrero.eclihand.model.exception.EclihandMessage;
import com.pedrero.eclihand.service.common.ServiceException;

public class EclihandAuthenticationException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6089059097328328127L;

	public EclihandAuthenticationException() {
	}

	public EclihandAuthenticationException(EclihandMessage message,
			Throwable cause) {
		super(message, cause);
	}

	public EclihandAuthenticationException(EclihandMessage message) {
		super(message);
	}

	public EclihandAuthenticationException(Throwable cause) {
		super(cause);
	}

}
