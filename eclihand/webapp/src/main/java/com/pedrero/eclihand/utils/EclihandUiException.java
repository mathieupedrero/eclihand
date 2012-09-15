package com.pedrero.eclihand.utils;

public class EclihandUiException extends RuntimeException {

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

	public EclihandUiException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
