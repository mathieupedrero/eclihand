package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.EclihandView;
import com.pedrero.eclihand.ui.panel.entity.PlayerPanel;
import com.pedrero.eclihand.ui.panel.entity.TeamPanel;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@org.springframework.stereotype.Component(value = "bodyPanel")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BodyPanel extends Panel implements Initiable {

	@Resource(name = "eclihandContentPanel")
	private Panel contentPanel;

	@Resource
	private HomeScreen homePanel;

	@Resource
	private TeamsScreen teamsPanel;

	@Resource
	private TeamPanel teamPanel;

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

	@Resource
	private EclihandNavigator eclihandNavigator;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7526198221763033359L;

	public void showTeamsPanel() {
		eclihandNavigator.navigateTo(teamsPanel);
	}

	public void showTeamPanel() {
		eclihandNavigator.navigateTo(teamPanel);
	}

	public void showPlayersPanel() {
		eclihandNavigator.navigateTo(playersPanel);
	}

	public void showPlayerPanel() {
		eclihandNavigator.navigateTo(playerPanel);
	}

	public void showComponent(EclihandView panel) {
		eclihandNavigator.navigateTo(panel);
	}

	public void showHomePanel() {
		eclihandNavigator.navigateTo(homePanel);
	}

	@PostConstruct
	public void postConstruct() {
		layout = eclihandLayoutFactory.createCommonHorizontalLayout();
		this.setContent(layout);
		layout.addComponent(leftPanel);
		layout.addComponent(contentPanel);
		contentPanel.setContent(homePanel);
		this.setCaption(messageResolver.getMessage("body.caption"));
	}

}
