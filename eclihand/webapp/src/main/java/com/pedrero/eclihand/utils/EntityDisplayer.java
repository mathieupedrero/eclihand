package com.pedrero.eclihand.utils;

import com.pedrero.eclihand.model.dto.DataObjectDto;

public interface EntityDisplayer<T extends DataObjectDto> {

	public abstract void display(T entity);

}