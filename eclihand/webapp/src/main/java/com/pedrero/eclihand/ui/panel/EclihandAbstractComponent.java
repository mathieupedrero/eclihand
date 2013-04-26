package com.pedrero.eclihand.ui.panel;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

public abstract class EclihandAbstractComponent extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646541775081297743L;

	public EclihandAbstractComponent() {
		super();
		init();
	}

	public EclihandAbstractComponent(ComponentContainer content) {
		super(content);
		init();
	}
	
	private void init(){
	}

}
