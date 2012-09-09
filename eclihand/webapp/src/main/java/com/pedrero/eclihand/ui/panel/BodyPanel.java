package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

@org.springframework.stereotype.Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BodyPanel extends Panel implements Initiable {

	@Resource
	private HomePanel homePanel;

	@Resource
	private TeamsPanel teamsPanel;

	@Resource
	private LeftPanel leftPanel;

	@Resource
	private MessageResolver messageResolver;

	private Component currentPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7526198221763033359L;

	@Override
	public void init() {
		this.setContent(new HorizontalLayout());
		this.addComponent(leftPanel);
		this.addComponent(homePanel);
		currentPanel = homePanel;
		this.setCaption(messageResolver.getMessage("body.caption"));

	}

	public void showTeamsPanel() {
		this.replaceComponent(currentPanel, teamsPanel);
		currentPanel = teamsPanel;
	}

	public void showComponent(Component panel) {
		this.replaceComponent(currentPanel, panel);
		currentPanel = panel;
	}

	public void showHomePanel() {
		this.replaceComponent(currentPanel, homePanel);
		currentPanel = homePanel;
	}

}
