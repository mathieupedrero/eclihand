package com.pedrero.eclihand.ui;

import com.pedrero.eclihand.servlet.SpringVaadinServlet;
import com.pedrero.eclihand.ui.window.EclihandMainWindow;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class EclihandMainUI extends UI {

	public EclihandMainUI() {
		super();
		eclihandMainWindow = SpringVaadinServlet.WEB_APPLICATION_CONTEXT
				.getBean(EclihandMainWindow.class);
	}

	private EclihandMainWindow eclihandMainWindow;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5162173188485477058L;

	public EclihandMainWindow getEclihandMainWindow() {
		return eclihandMainWindow;
	}

	public void setEclihandMainWindow(EclihandMainWindow eclihandMainWindow) {
		this.eclihandMainWindow = eclihandMainWindow;
	}

	@Override
	protected void init(VaadinRequest request) {
		eclihandMainWindow.init();
		setContent(eclihandMainWindow);

	}

}
