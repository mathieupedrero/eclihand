package com.pedrero.eclihand.ui.window;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.window.EclihandMainWindowController;
import com.pedrero.eclihand.ui.panel.BodyPanel;
import com.pedrero.eclihand.ui.panel.HeaderPanel;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@Component
public class EclihandMainWindow extends Panel implements Initiable {

	public EclihandMainWindow() {
		super();
		init();
	}

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

	private void init() {
		Layout layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);
		layout.addComponent(headerPanel);
		layout.addComponent(bodyPanel);
	}

}
