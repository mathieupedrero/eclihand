package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.ui.panel.LeftPanel;

@Controller
public class LeftPanelController implements EclihandController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 158082844439807392L;
	@Resource
	private LeftPanel leftPanel;


	@Override
	public void init() {
		leftPanel.init();
	}

}
