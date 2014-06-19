package com.pedrero.eclihand.controller.security;

import java.util.Set;

import com.pedrero.eclihand.model.domain.Credential;
import com.vaadin.ui.Panel;

public class SecuredComponent extends Panel implements ISecuredObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3562148187773232278L;

	@Override
	public Set<Credential> getRequiredCredentials() {
		return null;
	}

	@Override
	public ISecurityRule getSecurityRule() {
		return null;
	}

}
