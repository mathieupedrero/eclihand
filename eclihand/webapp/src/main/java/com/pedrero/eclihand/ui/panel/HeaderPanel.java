package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.HeaderPanelController;
import com.pedrero.eclihand.ui.menubar.MainMenuBar;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Layout;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HeaderPanel extends CustomComponent implements Initiable {

	@Resource
	private HeaderPanelController headerPanelController;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private MainMenuBar mainMenuBar;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3099650159349626440L;

	private void init() {
		layout = eclihandLayoutFactory.createCommonHorizontalLayout();
		this.setCompositionRoot(layout);
		layout.addComponent(mainMenuBar);
	}

	@PostConstruct
	protected void postConstruct() {
		init();
	}

}
