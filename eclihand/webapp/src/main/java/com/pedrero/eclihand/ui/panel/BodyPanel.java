package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.pedrero.eclihand.ui.panel.entity.AbstractEntityPanel;
import com.pedrero.eclihand.ui.panel.entity.PlayerPanel;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@org.springframework.stereotype.Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BodyPanel extends Panel implements Initiable, InitializingBean {

	@Resource
	private HomeScreen homePanel;

	@Resource
	private TeamsScreen teamsPanel;

	@Resource
	private AbstractEntityPanel teamPanel;

	@Resource
	private PlayersScreen playersPanel;

	@Resource
	private PlayerPanel playerPanel;

	@Resource
	private LeftScreen leftPanel;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Component currentPanel;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7526198221763033359L;

	private void init() {
		layout = eclihandLayoutFactory.createCommonHorizontalLayout();
		this.setContent(layout);
		layout.addComponent(leftPanel);
		layout.addComponent(homePanel);
		currentPanel = homePanel;
		this.setCaption(messageResolver.getMessage("body.caption"));

	}

	public void showTeamsPanel() {
		layout.replaceComponent(currentPanel, teamsPanel);
		currentPanel = teamsPanel;
	}

	public void showTeamPanel() {
		layout.replaceComponent(currentPanel, teamPanel);
		currentPanel = teamPanel;
	}

	public void showPlayersPanel() {
		layout.replaceComponent(currentPanel, playersPanel);
		currentPanel = playersPanel;
	}

	public void showPlayerPanel() {
		layout.replaceComponent(currentPanel, playerPanel);
		currentPanel = playerPanel;
	}

	public void showComponent(Component panel) {
		layout.replaceComponent(currentPanel, panel);
		currentPanel = panel;
	}

	public void showHomePanel() {
		layout.replaceComponent(currentPanel, homePanel);
		currentPanel = homePanel;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();

	}

}
