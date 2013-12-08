package com.pedrero.eclihand.ui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.vaadin.client.ui.VButton;

public class EclihandEntryPoint implements EntryPoint {
	@Override
	public void onModuleLoad() {
		// Create a button widget
		final VButton button = new VButton();
		button.setText("Click me!");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				button.setText("Hello, world!");
			}
		});
		RootPanel.get().add(button);
	}

}
