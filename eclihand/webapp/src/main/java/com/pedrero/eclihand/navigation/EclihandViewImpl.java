package com.pedrero.eclihand.navigation;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;

public abstract class EclihandViewImpl extends CustomComponent implements
		EclihandView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6356999947683977521L;

	// private Component uiComponent;

	@Override
	public void enter(ViewChangeEvent event) {
		retrieveAssociatedPlace().feedFromFragment(event.getParameters());
		display();
	}
	//
	// /**
	// * @return the uiComponent
	// */
	// public Component getUiComponent() {
	// return uiComponent;
	// }
	//
	// /**
	// * @param uiComponent
	// * the uiComponent to set
	// */
	// public void setUiComponent(Component uiComponent) {
	// this.uiComponent = uiComponent;
	// }

}
