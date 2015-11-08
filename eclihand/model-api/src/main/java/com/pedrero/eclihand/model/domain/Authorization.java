package com.pedrero.eclihand.model.domain;

public interface Authorization extends DataObject {

	public Credential getCredential();

	public void setCredential(Credential credential);

}