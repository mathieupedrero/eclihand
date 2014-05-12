package com.pedrero.eclihand.ui.panel;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pedrero.eclihand.controller.security.ISecuredObject;
import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.pedrero.eclihand.controller.security.SecurityComponentAttachListener;
import com.pedrero.eclihand.controller.security.SecurityController;
import com.pedrero.eclihand.model.domain.Credential;
import com.vaadin.ui.VerticalLayout;

public abstract class EclihandAbstractComponent extends VerticalLayout
		implements ISecuredObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646541775081297743L;

	@Resource
	private SecurityController securityController;

	@PostConstruct
	protected void postConstruct() {
		this.addComponentAttachListener(new SecurityComponentAttachListener(
				securityController, this));
	}

	private ISecurityRule securityRule;

	@Override
	public abstract Set<Credential> getRequiredCredentials();

	@Override
	public ISecurityRule getSecurityRule() {
		return securityRule;
	}

	public void setSecurityRule(ISecurityRule securityRule) {
		this.securityRule = securityRule;
	}

}
