package com.pedrero.eclihand.dao;

import java.util.List;

import com.pedrero.eclihand.model.domain.DataObject;

public interface DataObjectDao<T extends DataObject> {
	public T save(T entity);

	public T findById(Long id);

	public void delete(T entity);

	public void delete(Long id);

	void delete(Iterable<? extends T> entities);

	void deleteAll();

	public List<T> findAll();

	public boolean exists(Long id);

	long count();

}
