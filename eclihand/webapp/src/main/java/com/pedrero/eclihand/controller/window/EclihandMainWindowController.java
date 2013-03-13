package com.pedrero.eclihand.controller.window;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.WindowController;
import com.pedrero.eclihand.controller.panel.BodyPanelController;
import com.pedrero.eclihand.controller.panel.HeaderPanelController;
import com.pedrero.eclihand.ui.window.EclihandMainWindow;
import com.vaadin.ui.Window;

@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EclihandMainWindowController implements EclihandController,
		WindowController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5117454372676672562L;

	@Resource
	private EclihandMainWindow eclihandMainWindow;

	@Resource
	private HeaderPanelController headerPanelController;

	@Resource
	private BodyPanelController bodyPanelController;

	@Override
	public void init() {
		headerPanelController.init();
		bodyPanelController.init();
	}

	@Override
	public void addWindow(Window window) {
		eclihandMainWindow.getUI().addWindow(window);
	}
}
