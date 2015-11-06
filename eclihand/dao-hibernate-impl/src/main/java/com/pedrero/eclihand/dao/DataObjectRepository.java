package com.pedrero.eclihand.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pedrero.eclihand.dao.DataObjectDao;
import com.pedrero.eclihand.dao.request.PageableParamImpl;
import com.pedrero.eclihand.dao.request.SortParamImpl;
import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.dto.PageableDto;

@NoRepositoryBean
public interface DataObjectRepository<U extends DataObject, T extends U> extends DataObjectDao<U>,
		PagingAndSortingRepository<T, Long> {

	@Override
	public default List<? extends T> findAllObjects(PageableDto pageable) {
		return findAll(
				new PageableParamImpl(pageable.getPageNumber(), pageable.getPageSize(), new SortParamImpl(pageable
						.getSort().getColumnName(), pageable.getSort().isAscending()))).getContent();
	}

	@Override
	public default List<? extends T> findAllObjects() {
		List<T> result = new ArrayList<T>();
		findAll().forEach(e -> result.add(e));
		return result;
	}

}
