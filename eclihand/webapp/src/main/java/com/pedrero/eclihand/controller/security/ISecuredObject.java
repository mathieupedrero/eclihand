package com.pedrero.eclihand.controller.security;

import java.util.Set;

import com.pedrero.eclihand.model.domain.Credential;

public interface ISecuredObject {
	public Set<Credential> getRequiredCredentials();

	public ISecurityRule getSecurityRule();

}
