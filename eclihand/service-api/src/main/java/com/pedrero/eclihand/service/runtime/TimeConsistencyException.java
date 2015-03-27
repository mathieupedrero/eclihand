package com.pedrero.eclihand.service.runtime;

import com.pedrero.eclihand.service.common.ServiceException;

public class TimeConsistencyException extends ServiceException {

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
