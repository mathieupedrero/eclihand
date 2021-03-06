package com.pedrero.eclihand.ui.window;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.window.EclihandMainWindowController;
import com.pedrero.eclihand.ui.Authentication;
import com.pedrero.eclihand.ui.panel.BodyPanel;
import com.pedrero.eclihand.ui.panel.HeaderPanel;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EclihandMainWindow extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4719362644970678775L;

	@Resource
	private EclihandMainWindowController eclihandMainWindowController;

	@Resource
	private HeaderPanel headerPanel;

	@Resource
	private BodyPanel bodyPanel;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private Authentication authentication;

	@PostConstruct
	public void postConstruct() throws Exception {
		Layout layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);
		layout.addComponent(headerPanel);
		layout.addComponent(bodyPanel);
	}

}
