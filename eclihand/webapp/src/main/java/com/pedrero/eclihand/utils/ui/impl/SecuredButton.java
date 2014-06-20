package com.pedrero.eclihand.utils.ui.impl;

import com.pedrero.eclihand.controller.security.ISecuredObject;
import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.vaadin.ui.Button;

public class SecuredButton extends Button implements ISecuredObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520750446961646987L;

	private final ISecurityRule securityRule;

	public SecuredButton(ISecurityRule securityRule) {
		super();
		this.securityRule = securityRule;
	}

	public SecuredButton(String caption, ClickListener listener, ISecurityRule securityRule) {
		super(caption, listener);
		this.securityRule = securityRule;
	}

	public SecuredButton(String caption, ISecurityRule securityRule) {
		super(caption);
		this.securityRule = securityRule;
	}

	@Override
	public ISecurityRule getSecurityRule() {
		return securityRule;
	}

}
