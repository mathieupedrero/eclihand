package com.pedrero.eclihand.service.biz.transerval;

import java.util.List;

/**
 * Authentication context (Implemented by client)
 * 
 * @author mathieu.pedrero
 *
 */
public interface AuthenticationContext {
	/**
	 * @return name of the authenticated user
	 */
	String giveUsername();

	/**
	 * @return rights of the authenticated user
	 */
	List<String> giveRights();
}
