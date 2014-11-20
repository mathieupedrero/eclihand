package com.pedrero.eclihand.rest.security;

import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;

public class EclihandAuthenticationProvider extends DaoAuthenticationProvider {

	private static final String HMAC_SHA256_ALGORITHM_NAME = "HmacSHA256";

	@Override
	public boolean supports(Class<?> authentication) {
		return EclihandToken.class.equals(authentication);
	}

	private String calculateHMAC(String secret, String data) {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM_NAME);
			Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM_NAME);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			String result = new String(Base64.encode(rawHmac));
			return result;
		} catch (GeneralSecurityException e) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		EclihandToken restToken = (EclihandToken) authentication;

		// get secret access key from api key
		String secret = userDetails.getPassword();

		// if that username does not exist, throw exception
		if (secret == null) {
			throw new BadCredentialsException("Invalid username or password.");
		}

		// calculate the hmac of content with secret key
		String hmac = calculateHMAC(secret, restToken.getCredentials().getRequestData());
		// check if signatures match
		if (!restToken.getCredentials().getSignature().equals(hmac)) {
			throw new BadCredentialsException("Invalid username or password.");
		}
	}

}
