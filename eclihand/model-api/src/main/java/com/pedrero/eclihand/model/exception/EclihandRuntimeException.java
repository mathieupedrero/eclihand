package com.pedrero.eclihand.model.exception;

public class EclihandRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1871924666224330192L;

	public EclihandRuntimeException() {
		super();
	}

	public EclihandRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public EclihandRuntimeException(String message) {
		super(message);
	}

	public EclihandRuntimeException(Throwable cause) {
		super(cause);
	}

}
