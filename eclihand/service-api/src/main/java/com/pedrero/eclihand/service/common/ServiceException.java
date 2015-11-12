package com.pedrero.eclihand.service.common;

import com.pedrero.eclihand.model.exception.EclihandException;
import com.pedrero.eclihand.model.exception.EclihandMessage;

public class ServiceException extends EclihandException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5760528789489702513L;

	public ServiceException() {
	}

	public ServiceException(EclihandMessage message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(EclihandMessage message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
