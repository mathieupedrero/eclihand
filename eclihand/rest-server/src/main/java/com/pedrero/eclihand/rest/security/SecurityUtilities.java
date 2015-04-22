package com.pedrero.eclihand.rest.security;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtilities {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtilities.class);

	private static final String DATE_HEADER_NAME = "X-ecli-Date";
	private static final String HMAC_SHA256_ALGORITHM_NAME = "HmacSHA256";
	// Enable Multi-Read for PUT and POST requests
	private static final Set<String> METHOD_HAS_CONTENT = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	private final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

	static {
		METHOD_HAS_CONTENT.add("PUT");
		METHOD_HAS_CONTENT.add("POST");
	}

	public EclihandRequestContent buildRequestContentFrom(EclihandRequestWrapper requestWrapper) {
		return new EclihandRequestContent(requestWrapper.getRequestURI(),
				parseDate(requestWrapper.getHeader(DATE_HEADER_NAME)), requestWrapper.getMethod(),
				requestWrapper.getPayload(), requestWrapper.getContentType());
	}

	public String signRequest(String secret, EclihandRequestContent content) {
		LOGGER.debug("Going to sign request [{} {} {} {} {}] with secret {}", content.getUri(), content.getDate(),
				content.getMethod(), content.getContent(), content.getContentType(), secret);
		return calculateHMAC(secret, computeDataFragmentFrom(content));
	}

	public EclihandRequestCredentials buildCredentialsFrom(String userName, EclihandRequestContent content,
			String signature) {
		return new EclihandRequestCredentials(userName, content, signature);
	}

	public String computeDataFragmentFrom(EclihandRequestContent content) {
		// get md5 content and content-type if the request is POST or PUT method
		boolean hasContent = METHOD_HAS_CONTENT.contains(content.getMethod());

		String contentMd5 = hasContent ? md5PasswordEncoder.encodePassword(content.getContent(), null) : "";
		String contentType = hasContent ? content.getContentType() : "";

		// calculate content to sign
		StringBuilder toSign = new StringBuilder();
		toSign.append(content.getMethod()).append("\n").append(contentMd5).append("\n").append(contentType)
				.append("\n").append(printDate(content.getDate())).append("\n").append(content.getUri());
		return toSign.toString();
	}

	public Date parseDate(String dateString) {
		return DateUtils.parseDate(dateString);
	}

	public String printDate(Date date) {
		return DateUtils.formatDate(date);
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

}
