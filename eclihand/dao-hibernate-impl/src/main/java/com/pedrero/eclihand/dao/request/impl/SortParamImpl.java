package com.pedrero.eclihand.dao.request.impl;

import org.springframework.data.domain.Sort;

import com.pedrero.eclihand.dao.request.SortParam;

public class SortParamImpl extends Sort implements SortParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5763131360825918624L;

	private final String sortColumn;

	private final Boolean ascending;

	public SortParamImpl(String sortColumn, Boolean ascending) {
		super(new Order(ascending ? Direction.ASC : Direction.DESC, sortColumn));
		this.ascending = ascending;
		this.sortColumn = sortColumn;
	}

	@Override
	public String getSortColumn() {
		return sortColumn;
	}

	@Override
	public Boolean isAscending() {
		return ascending;
	}

}
