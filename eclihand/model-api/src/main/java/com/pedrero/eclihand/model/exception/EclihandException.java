package com.pedrero.eclihand.model.exception;

public class EclihandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1871924666224330192L;

	private final EclihandMessage eclihandMessage;

	public EclihandException() {
		super();
		this.eclihandMessage = null;
	}

	public EclihandException(EclihandMessage message, Throwable cause) {
		super(message.getKey(), cause);
		this.eclihandMessage = message;
	}

	public EclihandException(EclihandMessage message) {
		super(message.getKey());
		this.eclihandMessage = message;
	}

	public EclihandException(Throwable cause) {
		super(cause);
		this.eclihandMessage = null;
	}

	public EclihandMessage getEclihandMessage() {
		return eclihandMessage;
	}

}
