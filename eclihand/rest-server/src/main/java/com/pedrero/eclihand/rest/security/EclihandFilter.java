package com.pedrero.eclihand.rest.security;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

public class EclihandFilter extends GenericFilterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(EclihandFilter.class);

	// Enable Multi-Read for PUT and POST requests
	private static final Set<String> METHOD_HAS_CONTENT = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

	static {
		METHOD_HAS_CONTENT.add("PUT");
		METHOD_HAS_CONTENT.add("POST");
	}

	private final AuthenticationManager authenticationManager;
	private final AuthenticationEntryPoint authenticationEntryPoint;
	private final Md5PasswordEncoder md5;

	public EclihandFilter(AuthenticationManager authenticationManager) {
		this(authenticationManager, new EclihandAuthenticationEntryPoint());
	}

	public EclihandFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.md5 = new Md5PasswordEncoder();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		// use wrapper to read multiple times the content
		EclihandRequestWrapper request = new EclihandRequestWrapper((HttpServletRequest) req);
		HttpServletResponse response = (HttpServletResponse) resp;

		// Get authorization header
		String authorizationHeader = request.getHeader("Authorization");

		// If there's not credentials return...
		if (authorizationHeader == null) {
			chain.doFilter(request, response);
			return;
		}

		// Authorization header is in the form <public_access_key>:<signature>
		String auth[] = authorizationHeader.split(":");

		// get md5 content and content-type if the request is POST or PUT method
		boolean hasContent = METHOD_HAS_CONTENT.contains(request.getMethod());
		String contentMd5 = hasContent ? md5.encodePassword(request.getPayload(), null) : "";
		String contentType = hasContent ? request.getContentType() : "";

		// get timestamp
		String timestamp = request.getHeader("Date");

		// calculate content to sign
		StringBuilder toSign = new StringBuilder();
		toSign.append(request.getMethod()).append("\n").append(contentMd5).append("\n").append(contentType)
				.append("\n").append(timestamp).append("\n").append(request.getRequestURI());

		// a rest credential is composed by request data to sign and the
		// signature
		EclihandCredentials restCredential = new EclihandCredentials(toSign.toString(), auth[1]);

		// calculate UTC time from timestamp (usually Date header is GMT but
		// still...)
		Date date = null;
		try {
			date = DateUtils.parseDate(timestamp, new String[] { "EEE, dd MMM yyyy HH:mm:ss Z" });
		} catch (ParseException e) {
			LOGGER.warn("Error parsing http header date {}", timestamp, e);
		}

		// Create an authentication token
		Authentication authentication = null;
		authentication = new EclihandToken(auth[0], restCredential, date);

		try {
			// Request the authentication manager to authenticate the token
			// (throws exception)
			Authentication successfulAuthentication = authenticationManager.authenticate(authentication);

			// Pass the successful token to the SecurityHolder where it can be
			// retrieved by this thread at any stage.
			SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);
			// Continue with the Filters
			chain.doFilter(request, response);
		} catch (AuthenticationException authenticationException) {
			// If it fails clear this threads context and kick off the
			// authentication entry point process.
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(request, response, authenticationException);
		}
	}
}
