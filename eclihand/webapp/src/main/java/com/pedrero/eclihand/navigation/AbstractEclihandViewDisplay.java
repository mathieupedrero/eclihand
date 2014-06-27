package com.pedrero.eclihand.navigation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;

public abstract class AbstractEclihandViewDisplay extends AbstractEclihandComponent implements ViewDisplay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6233904835576538170L;

	@Override
	public void showView(View view) {
		assert view instanceof EclihandView;
		this.getMainLayout().removeAllComponents();
		this.getMainLayout().addComponent(((EclihandView) view).retrieveComponentToDisplay());

	}

}
