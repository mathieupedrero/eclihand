package com.pedrero.eclihand.rest.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class EclihandAuthenticationProvider extends DaoAuthenticationProvider {

	@Resource
	private SecurityUtilities securityUtilities;

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

		String expectedSignature = securityUtilities.signRequest(userDetails.getPassword(), restToken.getCredentials()
				.getContent());

		// check if signatures match
		if (!expectedSignature.equals(restToken.getCredentials().getSignature())) {
			throw new BadCredentialsException("Invalid username or password.");
		}
	}

}
