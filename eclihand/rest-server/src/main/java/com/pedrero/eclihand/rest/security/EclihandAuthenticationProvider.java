package com.pedrero.eclihand.rest.security;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.pedrero.eclihand.service.runtime.exception.EclihandAuthenticationException;

public abstract class EclihandAuthenticationProvider extends DaoAuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(EclihandAuthenticationProvider.class);

	@Resource
	private SecurityUtilities securityUtilities;

	@Override
	public boolean supports(Class<?> authentication) {
		return AuthenticationToken.class.equals(authentication);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		AuthenticationToken restToken = (AuthenticationToken) authentication;
		EclihandUser user = (EclihandUser) userDetails;

		if (user.isGuestUser()) {
			return;
		}

		String expectedSignature = securityUtilities.signRequest(userDetails.getPassword(), restToken.getCredentials()
				.getContent());

		// check if signatures match

		LOGGER.info("Checking request signature for user [{}]", userDetails.getUsername());

		if (!expectedSignature.equals(restToken.getCredentials().getSignature())) {
			LOGGER.warn("request signature check failed for user [{}]", userDetails.getUsername());
			throw new BadCredentialsException("Invalid request signature.");
		}
	}

	protected abstract String findSessionTokenFor(UserDetails userDetails, Date clientTimeRequest)
			throws EclihandAuthenticationException;

	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
			UserDetails user) {
		UsernamePasswordAuthenticationToken successAuthentication = (UsernamePasswordAuthenticationToken) super
				.createSuccessAuthentication(principal, authentication, user);

		EclihandUser eclihandUser = (EclihandUser) user;
		if (!eclihandUser.isGuestUser()) {
			AuthenticationToken restToken = (AuthenticationToken) authentication;
			try {
				String sessionToken = findSessionTokenFor(user, restToken.getCredentials().getContent().getDate());
				return new RunningSessionAuthenticationToken(successAuthentication, sessionToken);
			} catch (EclihandAuthenticationException e) {
				return successAuthentication;
			}
		}
		return successAuthentication;
	}

}
