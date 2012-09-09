package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.HeaderPanelController;
import com.pedrero.eclihand.ui.menubar.MainMenuBar;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.vaadin.ui.Panel;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HeaderPanel extends Panel implements Initiable {

	@Resource
	private HeaderPanelController headerPanelController;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private MainMenuBar mainMenuBar;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3099650159349626440L;

	@Override
	public void init() {
		this.setCaption(messageResolver.getMessage("header.caption"));
		this.addComponent(mainMenuBar);
	}

}
