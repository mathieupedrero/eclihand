package com.pedrero.eclihand.rest.security;


public final class EclihandCredentials {

	private final String requestData;
	private final String signature;

	public EclihandCredentials(String requestData, String signature) {
		this.requestData = requestData;
		this.signature = signature;
	}

	public String getRequestData() {
		return requestData;
	}

	public String getSignature() {
		return signature;
	}
}
