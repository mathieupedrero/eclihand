package com.pedrero.eclihand.rest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pedrero.eclihand.model.exception.EclihandMessage;
import com.pedrero.eclihand.model.exception.EclihandRuntimeException;

@ControllerAdvice
public class EclihandResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EclihandResponseEntityExceptionHandler.class);

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
			LOGGER.debug("Field validation exception ");
			BindingResult bindingResult = ex.getBindingResult();
			LOGGER.debug("\t\t Error Default Message [{}] ", bindingResult
					.getAllErrors().get(0).getDefaultMessage());
			LOGGER.debug("\t\t Error Default code [{}] ", bindingResult
					.getAllErrors().get(0).getCode());
			LOGGER.debug(
					"\t\t Error codes [{}] ",
					Arrays.asList(bindingResult.getAllErrors().get(0)
							.getCodes()));
			LOGGER.debug("\t\t Object Name [{}] ", bindingResult.getAllErrors()
					.get(0).getObjectName());
			LocaleContextHolder.getLocale();
		}
		return null;
	}

}
