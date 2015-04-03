package com.pedrero.eclihand.rest.security;

import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.biz.UserService;

public abstract class AbstractUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserDetailsService.class);

	@Resource
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

		final UserDto retrieved;
		final Boolean isGuest;
		if (arg0 == null) {
			retrieved = userService.retrieveGuestUser();
			isGuest = true;
		} else {
			retrieved = userService.retrieveByLogin(arg0);
			isGuest = false;
		}
		if (retrieved == null) {
			LOGGER.error("Username {} was not found", arg0);
			throw new UsernameNotFoundException("Username was not found");
		}
		Set<GrantedAuthority> authorities = retrieved
				.getProfiles()
				.stream()
				.flatMap((profile) -> profile.getAuthorizations().stream())
				.collect(
						Collectors.mapping((authorization) -> new SimpleGrantedAuthority(authorization.getCredential()
								.name()), Collectors.toSet()));

		if (isGuest) {
			return new EclihandUser(retrieved.getLogin(), authorities);
		}
		return new EclihandUser(retrieved.getLogin(), retrieveSecretSignKeyForUser(retrieved.getLogin()), authorities);
	}

	abstract protected String retrieveSecretSignKeyForUser(final String retrieved) throws UsernameNotFoundException;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
