package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.ui.panel.HomePanel;
import com.pedrero.eclihand.utils.Displayer;

@Controller
public class HomePanelController implements EclihandController, Displayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4059362004686066305L;

	@Resource
	private HomePanel homePanel;

	@Override
	public void init() {
		homePanel.init();
	}

	@Override
	public void display() {
		homePanel.display();
	}

}