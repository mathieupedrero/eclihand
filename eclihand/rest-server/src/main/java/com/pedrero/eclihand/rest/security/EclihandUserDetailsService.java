package com.pedrero.eclihand.rest.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.biz.UserService;

public class EclihandUserDetailsService implements UserDetailsService {

	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		UserDto retrieved = userService.retrieveByLogin(arg0);
		if (retrieved == null) {
			throw new UsernameNotFoundException("Username was not found");
		}
		Set<GrantedAuthority> authorities = retrieved
				.getProfiles()
				.stream()
				.flatMap((profile) -> profile.getAuthorizations().stream())
				.collect(
						Collectors.mapping(
								(authorization) -> new SimpleGrantedAuthority(
										authorization.getCredential().name()),
								Collectors.toSet()));
		return new User(retrieved.getLogin(), retrieved.getPassword(),
				authorities);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
