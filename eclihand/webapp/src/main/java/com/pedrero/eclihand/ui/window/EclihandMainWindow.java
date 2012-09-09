package com.pedrero.eclihand.ui.window;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.window.EclihandMainWindowController;
import com.pedrero.eclihand.ui.panel.BodyPanel;
import com.pedrero.eclihand.ui.panel.HeaderPanel;
import com.pedrero.eclihand.utils.Initiable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Component
public class EclihandMainWindow extends Window implements Initiable {
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

	@Override
	public void init() {
		this.setContent(new VerticalLayout());
		this.addComponent(headerPanel);
		this.addComponent(bodyPanel);

	}

}
