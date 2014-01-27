package com.pedrero.eclihand.ui.panel;

import java.util.Set;

import javax.annotation.PostConstruct;

import com.pedrero.eclihand.model.domain.Credential;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HasComponents.ComponentAttachEvent;

public abstract class EclihandAbstractComponent extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646541775081297743L;
	
	@PostConstruct
	public void postConstruct(){
		this.addComponentAttachListener(new ComponentAttachListener() {
			
			@Override
			public void componentAttachedToContainer(ComponentAttachEvent event) {
				// TODO Security Listener for event. Invoke bean Security Manager
				//EclihandAbstractComponent.this.removeComponent(event.getComponent());
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
