package com.pedrero.eclihand.rest.security;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtilities {
	private static final String HMAC_SHA256_ALGORITHM_NAME = "HmacSHA256";
	// Enable Multi-Read for PUT and POST requests
	private static final Set<String> METHOD_HAS_CONTENT = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	private final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

	static {
		METHOD_HAS_CONTENT.add("PUT");
		METHOD_HAS_CONTENT.add("POST");
	}

	public EclihandRequestCredentials computeCredentialsFrom(String userName, String secret, EclihandRequestContent content){
		String signature = calculateHMAC(secret, computeDataFragmentFrom(content));
		
		return new EclihandRequestCredentials(userName, content, signature);
	}

	public EclihandRequestCredentials buildCredentialsFrom(String userName, EclihandRequestContent content, String signature){
		return new EclihandRequestCredentials(userName, content, signature);
	}
	
	public String computeDataFragmentFrom(EclihandRequestContent content){
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
	
	private Date parseDate(String dateString){
		return new Date(Long.parseLong(dateString));
	}
	
	private String printDate(Date date){
		return Long.toString(date.getTime());
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
