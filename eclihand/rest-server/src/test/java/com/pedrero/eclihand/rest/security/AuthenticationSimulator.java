package com.pedrero.eclihand.rest.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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

public class AuthenticationSimulator {

	private static final SecurityUtilities SECURITY_UTILITIES = new SecurityUtilities();

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSimulator.class);

	public static void main(String[] args) throws IOException {
		testService("http://localhost:8080", "/eclihand-server/team/all", null, null);

	}

	public static void testService(final String path, final String uri, final String username, final String password)
			throws IOException {

		HttpGet request = new HttpGet(path + uri);

		Date javaDate = new Date();
		String date = SECURITY_UTILITIES.printDate(javaDate);

		EclihandRequestContent content = new EclihandRequestContent(uri, javaDate, request.getMethod(), "", null);

		request.addHeader(new BasicHeader("Date", date));

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			assert false;
		}

		if (username != null && password != null) {
			String encodedPassword = new String(Base64.encode(md.digest(password.getBytes())));
			String auth = username + ":" + SECURITY_UTILITIES.signRequest(encodedPassword, content);
			request.addHeader(new BasicHeader("Authorization", auth));
		}
		request.addHeader("Content-type", ContentType.APPLICATION_JSON.getMimeType());

		// send request
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpResponse response = client.execute(request);
			int status = response.getStatusLine().getStatusCode();
			String responseString = EntityUtils.toString(response.getEntity());
			if (status == 200) {
				LOGGER.info("Test d'autentification passé avec succès");
			} else {
				LOGGER.error("Echec du Test d'authentification - status {}", status);
			}
			LOGGER.info("Contenu[{}]", responseString);
		}

	}

}
