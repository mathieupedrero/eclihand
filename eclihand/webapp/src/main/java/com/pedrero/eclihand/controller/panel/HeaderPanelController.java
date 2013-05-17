package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.menubar.MainMenuBarController;
import com.pedrero.eclihand.ui.panel.HeaderPanel;

@Controller
public class HeaderPanelController implements EclihandController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 711502130121260850L;
	@Resource
	private HeaderPanel headerPanel;

	@Resource
	private MainMenuBarController mainMenuBarController;

}
