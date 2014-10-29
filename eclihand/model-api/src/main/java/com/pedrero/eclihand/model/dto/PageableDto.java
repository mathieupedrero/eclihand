package com.pedrero.eclihand.model.dto;

public class PageableDto {

	private Long pageNumber;

	private Long pageSize;

	private SortDto sort;

	public PageableDto() {
		super();
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public SortDto getSort() {
		return sort;
	}

	public void setSort(SortDto sort) {
		this.sort = sort;
	}

}