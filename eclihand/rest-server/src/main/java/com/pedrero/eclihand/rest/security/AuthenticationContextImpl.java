package com.pedrero.eclihand.rest.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.service.biz.transerval.AuthenticationContext;

@Component
public class AuthenticationContextImpl implements AuthenticationContext {

	@Override
	public String giveUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@Override
	public List<String> giveRights() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(auth -> auth.getAuthority()).collect(Collectors.toList());
	}

}
