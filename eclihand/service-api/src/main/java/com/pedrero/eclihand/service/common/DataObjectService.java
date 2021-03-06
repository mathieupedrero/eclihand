package com.pedrero.eclihand.service.common;

import java.util.List;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.model.dto.PageableDto;

public interface DataObjectService<T extends DataObjectDto> {
	public T save(T dto);

	public T update(T dto);

	public T findById(Long id);

	public void delete(T dto);

	public List<T> findAll();

	public List<T> findAll(PageableDto pageable);

	public List<T> searchByCriterium(Object criterium);

}
