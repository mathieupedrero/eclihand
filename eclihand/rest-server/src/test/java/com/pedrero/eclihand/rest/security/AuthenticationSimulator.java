package com.pedrero.eclihand.rest.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.security.crypto.codec.Base64;

public class AuthenticationSimulator {

	private static final SecurityUtilities SECURITY_UTILITIES = new SecurityUtilities();

	public static void main(String[] args) throws IOException {
		testService("http://localhost:8080", "/eclihand-server/teams", "admin",
				"admin");

	}

	public static void testService(final String path, final String uri,
			final String username, final String password) throws IOException {

		HttpPut request = new HttpPut(path + uri);

		// content plain text
		final String method = "POST";
		String contentToEncode = "{\"items\":[{\"id\":1,\"value\":3},{\"id\":234,\"value\":8}]}";
		StringEntity data = new StringEntity(contentToEncode,
				ContentType.APPLICATION_JSON);

		Date javaDate = new Date();
		String date = SECURITY_UTILITIES.printDate(javaDate);

		EclihandRequestContent content = new EclihandRequestContent(uri,
				javaDate, method, contentToEncode, data.getContentType()
						.getValue());

		request.addHeader(new BasicHeader("Date", date));

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			assert false;
		}

		String encodedPassword = new String(Base64.encode(md.digest(password
				.getBytes())));

		String auth = username + ":"
				+ SECURITY_UTILITIES.signRequest(encodedPassword, content);
		request.addHeader(new BasicHeader("Authorization", auth));

		// add data
		request.setEntity(data);

		// send request
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpResponse response = client.execute(request);
			int status = response.getStatusLine().getStatusCode();
			assert status == 200 : "Test failed";
		}

	}

}
