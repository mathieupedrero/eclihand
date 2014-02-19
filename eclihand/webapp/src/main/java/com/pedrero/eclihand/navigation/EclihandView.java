package com.pedrero.eclihand.navigation;

import com.vaadin.navigator.View;

public interface EclihandView extends View {

	public abstract EclihandPlace retrieveAssociatedPlace();
}
