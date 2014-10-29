package com.pedrero.eclihand.dao.impl;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pedrero.eclihand.dao.DataObjectDao;
import com.pedrero.eclihand.dao.request.PageableParam;
import com.pedrero.eclihand.model.domain.DataObject;

public interface DataObjectRepository<T extends DataObject, U extends T> extends DataObjectDao<T>,
		PagingAndSortingRepository<U, Long> {

	@Override
	public default Iterable<? extends T> findAll(PageableParam pageable) {
		return findAll(pageable);
	}

}
