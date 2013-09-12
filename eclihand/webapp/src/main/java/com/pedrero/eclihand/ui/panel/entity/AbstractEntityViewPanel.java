package com.pedrero.eclihand.ui.panel.entity;

import com.pedrero.eclihand.navigation.EclihandView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public abstract class AbstractEntityViewPanel extends AbstractEntityComponent
		implements EclihandView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093227224467715454L;

	@Override
	public void enter(ViewChangeEvent event) {
		retrieveAssociatedPlace().feedFromFragment(event.getParameters());
	}

}
