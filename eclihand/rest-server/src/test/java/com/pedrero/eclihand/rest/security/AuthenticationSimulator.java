package com.pedrero.eclihand.rest.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.security.crypto.codec.Base64;

public class AuthenticationSimulator {

	private static final String HMAC_SHA256_ALGORITHM_NAME = "HmacSHA256";

	public static void main(String[] args) throws IOException {
		testService("http://localhost:8080/eclihand-server/teams","toto","toto");

	}

	public static void testService(final String path, final String username, final String password) throws IOException {

		HttpPut request = new HttpPut(path);

		// content plain text
		String contentToEncode = "{\"items\":[{\"id\":1,\"value\":3},{\"id\":234,\"value\":8}]}";
		StringEntity data = new StringEntity(contentToEncode, ContentType.APPLICATION_JSON);

		String date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").format(new Date());

		// create signature: method + content md5 + content-type + date + uri
		StringBuilder signature = new StringBuilder();
		final String method = "POST";
		signature.append(method).append("\n").append(contentToEncode).append("\n").append(ContentType.APPLICATION_JSON.getMimeType()).append("\n")
		.append(date).append("\n").append(path);

		request.addHeader(new BasicHeader("Date", date));
		String auth = username + ":" + calculateHMAC(password, signature.toString());
		request.addHeader(new BasicHeader("Authorization", auth));

		// add data
		request.setEntity(data);

		// send request
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(request);

		int status = response.getStatusLine().getStatusCode();
		assert status == 200 : "Test failed";
	}

	private static String calculateHMAC(String secret, String data) {
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
