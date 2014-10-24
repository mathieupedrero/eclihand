package com.pedrero.eclihand.dao.request;


public interface PageableParam {

	/**
	 * Returns the page to be returned.
	 * 
	 * @return the page to be returned.
	 */
	int getPageNumber();

	/**
	 * Returns the number of items to be returned.
	 * 
	 * @return the number of items of that page
	 */
	int getPageSize();

	/**
	 * Returns the sorting parameters.
	 * 
	 * @return
	 */
	SortParam getSortOrder();

}
