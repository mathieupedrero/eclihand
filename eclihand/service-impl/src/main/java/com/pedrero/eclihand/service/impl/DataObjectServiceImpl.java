package com.pedrero.eclihand.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.converter.Converter;
import com.pedrero.eclihand.dao.DataObjectDao;
import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;

public abstract class DataObjectServiceImpl<T extends DataObjectDto, U extends DataObject> implements
		DataObjectService<T> {

	@Override
	@Transactional
	public T save(T dto) {
		U domain = getConverter().convertToEntity(dto);
		U saved = getDao().save(domain);
		return getConverter().convertToDto(saved);
	}

	@Override
	@Transactional
	public T update(T dto) {
		U domain = getDao().findById(dto.getId());
		getConverter().lightFeedEntityWithDto(domain, dto);
		return getConverter().convertToDto(domain);
	}

	@Override
	@Transactional
	public T findById(Long id) {
		U retrieved = getDao().findById(id);
		return getConverter().convertToDto(retrieved);
	}

	@Override
	@Transactional
	public void delete(T dto) {
		U toDelete = getConverter().convertToEntity(dto);
		getDao().delete(toDelete);

	}

	@Override
	@Transactional
	public List<T> findAll() {
		List<T> result = new ArrayList<T>();
		for (U entity : getDao().findAll()) {
			result.add(getConverter().convertToDto(entity));
		}
		return result;
	}

	@Override
	@Transactional
	public List<T> searchByCriterium(Object criterium) {
		List<T> result = new ArrayList<T>();
		for (U entity : getDao().findByIndexLikeIgnoreCase("%" + criterium.toString() + "%")) {
			result.add(getConverter().convertToDto(entity));
		}
		return result;
	}

	abstract public DataObjectDao<U> getDao();

	abstract public Converter<U, T> getConverter();

}
