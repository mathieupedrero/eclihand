package com.pedrero.eclihand.rest.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.time.ZonedDateTime;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

import com.pedrero.eclihand.model.exception.EclihandMessage;
import com.pedrero.eclihand.model.exception.EclihandRuntimeException;

public class AuthenticationSimulator {

	private static final SecurityUtilities SECURITY_UTILITIES = new SecurityUtilities();

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationSimulator.class);

	public static void main(String[] args) throws IOException {
		testService("http://localhost", "/eclihand-server/team/all");
		String token = testService("http://localhost",
				"/eclihand-server/authentication/touch", "admin", "admin", true);
		testService("http://localhost", "/eclihand-server/team/all", "admin",
				token, false);
		testService("http://localhost", "/eclihand-server/team/all", "admin",
				"admin", true);
		testService("http://localhost",
				"/eclihand-server/authentication/touch", "admin", "admin",
				false);
		testService("http://localhost",
				"/eclihand-server/authentication/touch", "admin", token, false);

	}

	private static MessageDigest getMessageDigestInstance() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new EclihandRuntimeException(new EclihandMessage(
					"error.message_digest_not_found"), e);
		}
	}

	private static byte[] concat(byte[] a, byte[] b) {
		int aLen = a.length;
		int bLen = b.length;
		byte[] c = new byte[aLen + bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

	public static String testService(final String path, final String uri,
			final String username, final String password, Boolean encode)
			throws IOException {

		HttpGet request = new HttpGet(path + uri);

		ZonedDateTime javaDate = ZonedDateTime.ofInstant(Clock.systemUTC()
				.instant(), Clock.systemUTC().getZone());
		String date = SECURITY_UTILITIES.printDate(javaDate);

		EclihandRequestContent content = new EclihandRequestContent(uri,
				javaDate, request.getMethod(), "", null);

		request.addHeader(new BasicHeader("Date", date));

		if (username != null && password != null) {
			final String auth;
			if (encode) {
				MessageDigest md = getMessageDigestInstance();
				byte[] sha256EncodedPassword = md.digest(password.getBytes());
				String encodedPassword = new String(Base64.encode(md
						.digest(concat(username.getBytes(),
								sha256EncodedPassword))));
				auth = username
						+ ":"
						+ SECURITY_UTILITIES.signRequest(encodedPassword,
								content);
			} else {
				auth = username + ":"
						+ SECURITY_UTILITIES.signRequest(password, content);
			}
			request.addHeader(new BasicHeader("Authorization", auth));
		}
		request.addHeader("Content-type",
				ContentType.APPLICATION_JSON.getMimeType());
		request.addHeader("X-ecli-Date", SECURITY_UTILITIES
				.printDate(ZonedDateTime.ofInstant(Clock.systemUTC().instant(),
						Clock.systemUTC().getZone())));

		// send request
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpResponse response = client.execute(request);
			int status = response.getStatusLine().getStatusCode();
			String responseString = EntityUtils.toString(response.getEntity());
			if (status == 200) {
				LOGGER.info("Test d'autentification passé avec succès");
			} else {
				LOGGER.error("Echec du Test d'authentification - status {}",
						status);
			}
			LOGGER.info("Contenu[{}]", responseString);
			Header tokenHeader = response.getFirstHeader("X-session-id");
			if (tokenHeader != null) {
				return tokenHeader.getValue();
			}
			return null;
		}

	}

	public static String testService(final String path, final String uri)
			throws IOException {
		return testService(path, uri, null, null, null);

	}
}
