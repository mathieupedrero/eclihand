package com.pedrero.eclihand.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pedrero.eclihand.model.exception.EclihandMessage;
import com.pedrero.eclihand.model.exception.EclihandRuntimeException;

@ControllerAdvice
public class EclihandResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(EclihandResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = EclihandRuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public EclihandMessage handleException(EclihandRuntimeException ex) {
		return ex.getEclihandMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public EclihandMessage handleException(MethodArgumentNotValidException ex) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Field validation exception {}", ex.getBindingResult().getAllErrors().get(0)
					.getDefaultMessage(), ex);
			LocaleContextHolder.getLocale();
		}
		return null;
	}

}
