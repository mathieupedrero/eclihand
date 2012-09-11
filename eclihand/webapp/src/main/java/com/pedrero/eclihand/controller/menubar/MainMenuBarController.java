package com.pedrero.eclihand.controller.menubar;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.panel.BodyPanelController;
import com.pedrero.eclihand.ui.menubar.MainMenuBar;

@Controller
public class MainMenuBarController extends Object implements EclihandController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8339521528456149254L;

	@Resource
	private MainMenuBar mainMenuBar;

	@Resource
	private BodyPanelController bodyPanelController;

	@Override
	public void init() {
		mainMenuBar.init();
	}

	public void goToHome() {
		bodyPanelController.showHomePanel();
	}

	public void goToTeams() {
		bodyPanelController.showTeamsPanel();
	}

}