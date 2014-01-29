package com.pedrero.eclihand.ui.panel;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pedrero.eclihand.controller.security.ISecuredObject;
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
	public void postConstruct() {
		this.addComponentAttachListener(new ComponentAttachListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3627695762622287208L;

			@Override
			public void componentAttachedToContainer(ComponentAttachEvent event) {
				if (!securityController.canBeShown(event.getComponent())) {
					EclihandAbstractComponent.this.removeComponent(event
							.getComponent());
				}
			}
		});
	}

	private Set<Credential> requiredCredentials;

	public Set<Credential> getRequiredCredentials() {
		return requiredCredentials;
	}

	public void setRequiredCredentials(Set<Credential> requiredCredentials) {
		this.requiredCredentials = requiredCredentials;
	}

}
