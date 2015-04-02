package com.pedrero.eclihand.service.biz;

import java.util.Set;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.common.DataObjectService;
import com.pedrero.eclihand.service.runtime.exception.BadCredentialsException;

public interface UserService extends DataObjectService<UserDto> {

	/**
	 * Method that retrieves the credentials for the submitted user.
	 * 
	 * @param user
	 * @return
	 */
	public Set<Credential> retrieveCredentialsFor(UserDto user);

	/**
	 * MEthod that retrieves the application guest user
	 * 
	 * @return
	 */
	public UserDto retrieveGuestUser();

	/**
	 * Try to with the submitted credentials.
	 * 
	 * @param login
	 * @param md5EncodedPassword
	 * @return
	 */
	public void checkCredentials(String login, String md5EncodedPassword) throws BadCredentialsException;

	/**
	 * Retrieves an uver given it's login.
	 * 
	 * @param login
	 * @return
	 */
	public UserDto retrieveByLogin(String login);

}
