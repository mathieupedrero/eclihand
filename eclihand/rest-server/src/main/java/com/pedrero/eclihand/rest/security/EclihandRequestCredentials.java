package com.pedrero.eclihand.rest.security;

public class EclihandRequestCredentials {

	private final String username;
	private final EclihandRequestContent content;
	private final String signature;

	public EclihandRequestCredentials(String username, EclihandRequestContent content, String signature) {
		super();
		this.username = username;
		this.content = content;
		this.signature = signature;
	}

	public String getUsername() {
		return username;
	}

	public EclihandRequestContent getContent() {
		return content;
	}

	public String getSignature() {
		return signature;
	}

}
