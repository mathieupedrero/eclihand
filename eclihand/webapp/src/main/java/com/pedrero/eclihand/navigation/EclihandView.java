package com.pedrero.eclihand.navigation;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface EclihandView extends View {

	EclihandPlace retrieveAssociatedPlace();

	Component retrieveComponentToDisplay();
}
