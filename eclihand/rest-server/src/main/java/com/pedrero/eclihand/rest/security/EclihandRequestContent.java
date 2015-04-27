package com.pedrero.eclihand.rest.security;

import java.time.ZonedDateTime;

public final class EclihandRequestContent {
	private final String uri;
	private final ZonedDateTime date;
	private final String method;
	private final String content;
	private final String contentType;

	public EclihandRequestContent(String uri, ZonedDateTime date, String method, String content, String contentType) {
		super();
		this.uri = uri;
		this.date = date;
		this.method = method;
		this.content = content;
		this.contentType = contentType;
	}

	public String getUri() {
		return uri;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public String getMethod() {
		return method;
	}

	public String getContent() {
		return content;
	}

	public String getContentType() {
		return contentType;
	}
}
