package com.pedrero.eclihand.dao.request.factory;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.dao.request.PageableParam;
import com.pedrero.eclihand.dao.request.PageableParamImpl;
import com.pedrero.eclihand.dao.request.SortParamImpl;
import com.pedrero.eclihand.dao.request.factory.PageableFactory;
import com.pedrero.eclihand.model.dto.PageableDto;

@Component
public class PageableFactoryImpl implements PageableFactory {

	@Override
	public PageableParam createFrom(PageableDto pageableDto) {
		return new PageableParamImpl(pageableDto.getPageNumber(), pageableDto.getPageSize(), new SortParamImpl(
				pageableDto.getSort().getColumnName(), pageableDto.getSort().isAscending()));
	}

}
