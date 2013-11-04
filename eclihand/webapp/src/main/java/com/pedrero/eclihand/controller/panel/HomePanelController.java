package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.ui.panel.HomeScreen;
import com.pedrero.eclihand.utils.Displayer;

@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomePanelController implements EclihandController, Displayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4059362004686066305L;

	@Resource
	private HomeScreen homePanel;

	@Override
	public void display() {
		homePanel.display();
	}

}
