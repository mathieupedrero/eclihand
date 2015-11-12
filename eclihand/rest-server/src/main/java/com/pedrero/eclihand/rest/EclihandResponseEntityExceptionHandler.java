package com.pedrero.eclihand.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pedrero.eclihand.model.exception.EclihandMessage;
import com.pedrero.eclihand.model.exception.EclihandRuntimeException;

@ControllerAdvice
public class EclihandResponseEntityExceptionHandler extends
		ResponseEntityExceptionHandler {

	@ExceptionHandler(value = EclihandRuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public EclihandMessage handleException(EclihandRuntimeException ex) {
		return ex.getEclihandMessage();
	}

}
