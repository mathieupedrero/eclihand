package com.pedrero.eclihand.navigation;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;

public abstract class AbstractEclihandView extends AbstractEclihandComponent implements EclihandView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6356999947683977521L;

	@Override
	public void enter(ViewChangeEvent event) {
		retrieveAssociatedPlace().feedFromFragment(event.getParameters());
	}

	@Override
	public Component retrieveComponentToDisplay() {
		return getComponent();
	}

}
