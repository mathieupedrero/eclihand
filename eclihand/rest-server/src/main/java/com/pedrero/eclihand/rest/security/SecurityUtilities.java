package com.pedrero.eclihand.rest.security;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Set;
import java.util.TreeSet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.exception.EclihandRuntimeException;

@Component
public class SecurityUtilities {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtilities.class);

	private static final String DATE_HEADER_NAME = "X-ecli-Date";
	private static final String HMAC_SHA256_ALGORITHM_NAME = "HmacSHA256";
	// Enable Multi-Read for PUT and POST requests
	private static final Set<String> METHOD_HAS_CONTENT = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

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

		MessageDigest messageDigest;
		messageDigest = getMessageDigestInstance();

		String contentMd5 = hasContent ? Base64.getEncoder().encodeToString(
				messageDigest.digest(content.getContent().getBytes())) : "";
		String contentType = hasContent ? content.getContentType() : "";

		// calculate content to sign
		StringBuilder toSign = new StringBuilder();
		toSign.append(content.getMethod()).append(contentMd5).append(contentType).append(printDate(content.getDate()))
				.append(content.getUri());
		return toSign.toString();
	}

	private MessageDigest getMessageDigestInstance() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new EclihandRuntimeException("Couldn't find message digest", e);
		}
	}

	public ZonedDateTime parseDate(String dateString) {
		return ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
	}

	public String printDate(ZonedDateTime date) {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(date);
	}

	private String calculateHMAC(String secret, String data) {
		try {
			LOGGER.debug("HMAC sources - data = {}, secret = {}", data, secret);
			SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM_NAME);
			Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM_NAME);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			String result = new String(Base64.getEncoder().encode(rawHmac));
			LOGGER.debug("Calculated HMAC = {}", result);
			return result;
		} catch (GeneralSecurityException e) {
			throw new IllegalArgumentException();
		}
	}

}
