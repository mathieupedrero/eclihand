package com.pedrero.eclihand.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pedrero.eclihand.model.exception.EclihandMessage;
import com.pedrero.eclihand.model.exception.EclihandRuntimeException;

@ControllerAdvice
public class EclihandResponseEntityExceptionHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(EclihandResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = EclihandRuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public EclihandMessage handleException(EclihandRuntimeException ex) {
		return ex.getEclihandMessage();
	}

	@ExceptionHandler(value = RuntimeException.class)
	public void handle(RuntimeException e) {
		LOGGER.info("Returning HTTP 400 Bad Request", e);
		e.printStackTrace();
		throw e;
	}

}
