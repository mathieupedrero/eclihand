package com.pedrero.eclihand.rest.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.pedrero.eclihand.service.runtime.RuntimeService;
import com.pedrero.eclihand.service.runtime.exception.EclihandAuthenticationException;

public class EclihandAuthenticationProvider extends DaoAuthenticationProvider {

	@Resource
	private SecurityUtilities securityUtilities;

	@Resource
	private RuntimeService runtimeService;

	@Override
	public boolean supports(Class<?> authentication) {
		return EclihandToken.class.equals(authentication);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		EclihandToken restToken = (EclihandToken) authentication;
		EclihandUser user = (EclihandUser) userDetails;

		if (user.isGuestUser()) {
			return;
		}
		try {
			runtimeService.checkRequestTimeConsistencyForUser(userDetails.getUsername(), restToken.getCredentials()
					.getContent().getDate());

			String expectedSignature = securityUtilities.signRequest(userDetails.getPassword(), restToken
					.getCredentials().getContent());

			// check if signatures match
			if (!expectedSignature.equals(restToken.getCredentials().getSignature())) {
				throw new BadCredentialsException("Invalid username or password.");
			}
		} catch (EclihandAuthenticationException e) {
			throw new BadCredentialsException("Bad credentials. Try to log in again", e);
		}
	}

}
