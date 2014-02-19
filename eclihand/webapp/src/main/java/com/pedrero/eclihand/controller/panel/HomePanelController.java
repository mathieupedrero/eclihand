package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.ui.panel.HomeScreen;

@Controller
public class HomePanelController implements EclihandController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4059362004686066305L;

	@Resource
	private HomeScreen homePanel;

}
