package com.pedrero.eclihand.utils.ui.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.pedrero.eclihand.controller.security.ISecuredObject;
import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.pedrero.eclihand.model.domain.Credential;
import com.vaadin.ui.Button;

public class SecuredButton extends Button implements ISecuredObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520750446961646987L;

	private final Set<Credential> credentials;

	private final ISecurityRule securityRule;

	public SecuredButton(Set<Credential> credentials) {
		super();
		this.credentials = credentials;
		this.securityRule = null;
	}

	public SecuredButton(Credential... credentials) {
		this(new HashSet<Credential>(Arrays.asList(credentials)));
	}

	public SecuredButton(String caption, ClickListener listener, Set<Credential> credentials) {
		super(caption, listener);
		this.credentials = credentials;
		this.securityRule = null;
	}

	public SecuredButton(String caption, ClickListener listener, Credential... credentials) {
		this(caption, listener, new HashSet<Credential>(Arrays.asList(credentials)));
	}

	public SecuredButton(String caption, Set<Credential> credentials) {
		super(caption);
		this.credentials = credentials;
		this.securityRule = null;
	}

	public SecuredButton(String caption, Credential... credentials) {
		this(caption, new HashSet<Credential>(Arrays.asList(credentials)));
	}

	public SecuredButton(ISecurityRule securityRule) {
		super();
		this.credentials = null;
		this.securityRule = securityRule;
	}

	public SecuredButton(String caption, ClickListener listener, ISecurityRule securityRule) {
		super(caption, listener);
		this.credentials = null;
		this.securityRule = securityRule;
	}

	public SecuredButton(String caption, ISecurityRule securityRule) {
		super(caption);
		this.credentials = null;
		this.securityRule = securityRule;
	}

	@Override
	public Set<Credential> getRequiredCredentials() {
		return credentials;
	}

	@Override
	public ISecurityRule getSecurityRule() {
		return securityRule;
	}

}
