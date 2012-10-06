package com.pedrero.eclihand.utils;

public class EclihandUiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6474255049253637667L;

	public EclihandUiException() {
	}

	public EclihandUiException(String message) {
		super(message);
	}

	public EclihandUiException(Throwable cause) {
		super(cause);
	}

	public EclihandUiException(String message, Throwable cause) {
		super(message, cause);
	}

}
