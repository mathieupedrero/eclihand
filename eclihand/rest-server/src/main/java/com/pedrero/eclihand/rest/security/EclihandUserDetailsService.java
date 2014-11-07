package com.pedrero.eclihand.rest.security;

import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.UserService;

@Component
public class EclihandUserDetailsService implements UserDetailsService {

	@Resource
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		UserDto retrieved = userService.retrieveByLogin(arg0);
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
		return new User(retrieved.getLogin(), retrieved.getPassword(), authorities);
	}

}
