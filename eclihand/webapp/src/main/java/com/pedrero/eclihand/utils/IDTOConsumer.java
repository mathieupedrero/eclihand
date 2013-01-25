package com.pedrero.eclihand.utils;

import com.pedrero.eclihand.model.dto.DataObjectDto;

public interface IDTOConsumer<T extends DataObjectDto> {

	public abstract void consume(T entity);

	public abstract void consume(Iterable<T> entity);

}