package com.pedrero.eclihand.converter.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.Converter;
import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.dto.DataObjectDto;

@Component
public abstract class ConverterImpl<T extends DataObject, U extends DataObjectDto>
		implements Converter<T, U> {
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public DozerBeanMapper getDozerBeanMapper() {
		return dozerBeanMapper;
	}

	public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
		this.dozerBeanMapper = dozerBeanMapper;
	}
	
}
