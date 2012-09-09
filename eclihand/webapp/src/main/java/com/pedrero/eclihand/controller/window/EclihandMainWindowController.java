package com.pedrero.eclihand.controller.window;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.panel.BodyPanelController;
import com.pedrero.eclihand.controller.panel.HeaderPanelController;
import com.pedrero.eclihand.ui.window.EclihandMainWindow;

@Controller
public class EclihandMainWindowController implements EclihandController {

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

		eclihandMainWindow.init();
	}
}
