package com.pedrero.eclihand.service.impl.biz;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.converter.UserConverter;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.UserDao;
import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.domain.UserType;
import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.biz.PlayerService;
import com.pedrero.eclihand.service.biz.UserService;
import com.pedrero.eclihand.service.runtime.exception.BadCredentialsException;

@Service
@Transactional
public class UserServiceImpl extends DataObjectServiceImpl<UserDto, User> implements UserService {

	public static final String AGE_WHEN_PLAYING_FOR_TEAM = "age.when.playing.for.team";

	@Resource
	private UserConverter userConverter;

	@Resource
	private UserDao userDao;

	@Resource
	private PlayerDao playerDao;

	@Resource
	private PlayerService playerService;

	@Override
	public UserConverter getConverter() {
		return userConverter;
	}

	public void setTeamConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	@Override
	public UserDao getDao() {
		return userDao;
	}

	public void setTeamDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Set<Credential> retrieveCredentialsFor(UserDto user) {
		Set<Credential> credentials = new HashSet<Credential>();
		for (Profile profile : getDao().findById(user.getId()).getProfiles()) {
			for (Authorization authorization : profile.getAuthorizations()) {
				credentials.add(authorization.getCredential());
			}
		}
		return credentials;
	}

	@Override
	public UserDto retrieveGuestUser() {
		User guestUser = getDao().findByUserType(UserType.GUEST);
		return getConverter().convertToDto(guestUser);
	}

	@Override
	public void checkCredentials(String login, String md5EncodedPassword) throws BadCredentialsException {
		User loggedIn = getDao().findByLoginAndPassword(login, md5EncodedPassword);
		if (loggedIn == null) {
			throw new BadCredentialsException();
		}
	}

	/**
	 * @see com.pedrero.eclihand.service.biz.UserService#retrieveByLogin(java.lang.String)
	 */
	@Override
	public UserDto retrieveByLogin(String login) {
		return getConverter().convertToDto(getDao().findByLogin(login));
	}

	/**
	 * @see com.pedrero.eclihand.service.biz.UserService#retrievePasswordByLogin(java.lang.String)
	 */
	@Override
	public String retrievePasswordByLogin(String login) {
		return getDao().findByLogin(login).getPassword();
	}

}
