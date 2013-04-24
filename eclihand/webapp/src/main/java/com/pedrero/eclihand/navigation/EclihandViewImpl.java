package com.pedrero.eclihand.navigation;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

public class EclihandViewImpl extends Panel implements EclihandView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6356999947683977521L;

	private EclihandPlace associatedPlace;

	private Component uiComponent;

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return null;
	}

	/**
	 * @return the associatedPlace
	 */
	public EclihandPlace getAssociatedPlace() {
		return associatedPlace;
	}

	/**
	 * @param associatedPlace the associatedPlace to set
	 */
	public void setAssociatedPlace(EclihandPlace associatedPlace) {
		this.associatedPlace = associatedPlace;
	}

	/**
	 * @return the uiComponent
	 */
	public Component getUiComponent() {
		return uiComponent;
	}

	/**
	 * @param uiComponent
	 *            the uiComponent to set
	 */
	public void setUiComponent(Component uiComponent) {
		this.uiComponent = uiComponent;
	}

}
