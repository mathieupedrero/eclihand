package com.pedrero.eclihand.converter;

import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.dto.DataObjectDto;

public interface Converter<T extends DataObject, U extends DataObjectDto> {
	public U convertToDto(T domain);

	public T convertToEntity(U dto);
}
