package com.pedrero.eclihand.dao;

import java.util.List;

import com.pedrero.eclihand.dao.request.PageableParam;
import com.pedrero.eclihand.model.domain.DataObject;

public interface DataObjectDao<T extends DataObject> {
	/**
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	public T findById(Long id);

	public void delete(T entity);

	public void delete(Long id);

	void delete(Iterable<? extends T> entities);

	void deleteAll();

	public Iterable<? extends T> findAll();

	public Iterable<? extends T> findAll(PageableParam pageable);

	public boolean exists(Long id);

	long count();

	public List<? extends T> findByIndexLikeIgnoreCase(String index);

}
