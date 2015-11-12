package com.pedrero.eclihand.model.exception;

public class EclihandRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1871924666224330192L;

	private final EclihandMessage eclihandMessage;

	public EclihandRuntimeException() {
		super();
		this.eclihandMessage = null;
	}

	public EclihandRuntimeException(EclihandMessage message, Throwable cause) {
		super(message.getKey(), cause);
		this.eclihandMessage = message;
	}

	public EclihandRuntimeException(EclihandMessage message) {
		super(message.getKey());
		this.eclihandMessage = message;
	}

	public EclihandRuntimeException(Throwable cause) {
		super(cause);
		this.eclihandMessage = null;
	}

	public EclihandMessage getEclihandMessage() {
		return eclihandMessage;
	}

}
