package com.pedrero.eclihand.dao.request.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pedrero.eclihand.dao.request.PageableParam;

public class PageableParamImpl implements PageableParam, Pageable {

	private final Long pageNumber;

	private final Long pageSize;

	private final SortParamImpl sortParamImpl;

	private final PageRequest delegate;

	private PageableParamImpl(Pageable from, SortParamImpl sortParamImpl) {
		this(new Long(from.getPageNumber()), new Long(from.getPageSize()), sortParamImpl);
	}

	public PageableParamImpl(Long pageNumber, Long pageSize, SortParamImpl sortParamImpl) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sortParamImpl = sortParamImpl;
		this.delegate = new PageRequest(pageNumber.intValue(), pageSize.intValue(), sortParamImpl);
	}

	@Override
	public int getPageNumber() {
		return pageNumber.intValue();
	}

	@Override
	public int getPageSize() {
		return pageSize.intValue();
	}

	@Override
	public int getOffset() {
		return delegate.getOffset();
	}

	@Override
	public SortParamImpl getSortOrder() {
		return sortParamImpl;
	}

	@Override
	public PageableParamImpl next() {
		return new PageableParamImpl(delegate.next(), sortParamImpl);
	}

	@Override
	public PageableParamImpl previousOrFirst() {
		return new PageableParamImpl(delegate.previousOrFirst(), sortParamImpl);
	}

	@Override
	public PageableParamImpl first() {
		return new PageableParamImpl(delegate.first(), sortParamImpl);
	}

	@Override
	public boolean hasPrevious() {
		return delegate.hasPrevious();
	}

	@Override
	public Sort getSort() {
		return delegate.getSort();
	}

}
