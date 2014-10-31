package com.pedrero.eclihand.dao;

import java.util.List;

import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.dto.PageableDto;

public interface DataObjectDao<T extends DataObject> {
	/**
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	public T findById(Long id);

	public void delete(T entity);

	public void delete(Long id);

	void deleteAll();

	public List<? extends T> findAllObjects();

	public List<? extends T> findAllObjects(PageableDto pageable);

	public boolean exists(Long id);

	long count();

	public List<? extends T> findByIndexLikeIgnoreCase(String index);

}
