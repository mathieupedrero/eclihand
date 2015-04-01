package com.pedrero.eclihand.rest.security;

import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pedrero.eclihand.model.domain.UserType;
import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.biz.UserService;
import com.pedrero.eclihand.service.runtime.RuntimeService;
import com.pedrero.eclihand.service.runtime.exception.NoCurrentSessionException;

public class EclihandUserDetailsService implements UserDetailsService {

	@Resource
	private UserService userService;

	@Resource
	private RuntimeService runtimeService;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

		final UserDto retrieved;
		if (arg0 == null) {
			retrieved = userService.retrieveGuestUser();
		} else {
			retrieved = userService.retrieveByLogin(arg0);
		}
		if (retrieved == null) {
			throw new UsernameNotFoundException("Username was not found");
		}
		Set<GrantedAuthority> authorities = retrieved
				.getProfiles()
				.stream()
				.flatMap((profile) -> profile.getAuthorizations().stream())
				.collect(
						Collectors.mapping((authorization) -> new SimpleGrantedAuthority(authorization.getCredential()
								.name()), Collectors.toSet()));
		try {
			return new EclihandUser(retrieved.getLogin(), runtimeService.findTokenFor(retrieved.getLogin()),
					authorities, UserType.GUEST == retrieved.getUserType());
		} catch (NoCurrentSessionException e) {
			throw new UsernameNotFoundException("No session exists for user", e);
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
