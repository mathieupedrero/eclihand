package com.pedrero.eclihand.navigation;

import com.pedrero.eclihand.utils.Displayer;
import com.vaadin.navigator.View;

public interface EclihandView extends View, Displayer {

	public abstract EclihandPlace retrieveAssociatedPlace();
}
