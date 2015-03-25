package com.pedrero.eclihand.rest.security;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

public class EclihandFilter extends GenericFilterBean {

	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

	// Enable Multi-Read for PUT and POST requests
	private static final Set<String> METHOD_HAS_CONTENT = new TreeSet<String>(
			String.CASE_INSENSITIVE_ORDER);

	static {
		METHOD_HAS_CONTENT.add("PUT");
		METHOD_HAS_CONTENT.add("POST");
	}

	@Resource
	private SecurityUtilities securityUtilities;

	private final AuthenticationManager authenticationManager;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	public EclihandFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// use wrapper to read multiple times the content
		EclihandRequestWrapper request = new EclihandRequestWrapper(
				(HttpServletRequest) req);
		HttpServletResponse response = (HttpServletResponse) resp;

		// Get authorization header
		String authorizationHeader = request
				.getHeader(AUTHORIZATION_HEADER_NAME);

		// If there's not credentials return...
		if (authorizationHeader == null) {
			chain.doFilter(request, response);
			return;
		}

		// Authorization header is in the form <public_access_key>:<signature>
		String auth[] = authorizationHeader.split(":");

		EclihandRequestContent requestContent = securityUtilities
				.buildRequestContentFrom(request);

		String userName = auth[0];
		String signature = auth[1];

		EclihandRequestCredentials restCredential = securityUtilities
				.buildCredentialsFrom(userName, requestContent, signature);

		// Create an authentication token
		Authentication authentication = new EclihandToken(userName,
				restCredential);

		try {
			// Request the authentication manager to authenticate the token
			// (throws exception)
			Authentication successfulAuthentication = authenticationManager
					.authenticate(authentication);

			// Pass the successful token to the SecurityHolder where it can be
			// retrieved by this thread at any stage.
			SecurityContextHolder.getContext().setAuthentication(
					successfulAuthentication);
			// Continue with the Filters
			chain.doFilter(request, response);
		} catch (AuthenticationException authenticationException) {
			// If it fails clear this threads context and kick off the
			// authentication entry point process.
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(request, response,
					authenticationException);
		}
	}
}
