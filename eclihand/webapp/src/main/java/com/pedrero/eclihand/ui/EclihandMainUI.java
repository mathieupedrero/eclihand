package com.pedrero.eclihand.ui;

import com.pedrero.eclihand.servlet.SpringVaadinServlet;
import com.pedrero.eclihand.ui.window.EclihandMainWindow;
import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class EclihandMainUI extends UI {

	public EclihandMainUI() {
		super();
		eclihandMainWindow = SpringVaadinServlet.WEB_APPLICATION_CONTEXT
				.getBean(EclihandMainWindow.class);
		localeContainer = SpringVaadinServlet.WEB_APPLICATION_CONTEXT
				.getBean(LocaleContainer.class);
	}

	private EclihandMainWindow eclihandMainWindow;

	private LocaleContainer localeContainer;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5162173188485477058L;

	@Override
	protected void init(VaadinRequest request) {
		eclihandMainWindow.init();
		setContent(eclihandMainWindow);
		localeContainer.setLocale(request.getLocale());
	}

}
