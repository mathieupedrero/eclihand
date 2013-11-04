package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public abstract class EclihandAbstractComponent extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646541775081297743L;

	@PostConstruct
	protected void postConstruct() {
		this.setCompositionRoot(new VerticalLayout());
	}

}
