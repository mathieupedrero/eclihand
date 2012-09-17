package com.pedrero.eclihand.service;

import java.util.List;

import com.pedrero.eclihand.model.dto.DataObjectDto;

public interface DataObjectService<T extends DataObjectDto> {
	public T save(T dto);

	public T update(T dto);

	public T findById(Long id);

	public void delete(T dto);

	public List<T> findAll();
	
	public List<T> searchByCriterium(Object criterium);

}
