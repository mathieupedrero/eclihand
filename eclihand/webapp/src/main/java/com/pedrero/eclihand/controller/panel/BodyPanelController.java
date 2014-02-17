package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.navigation.EclihandNavigatorImpl;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.navigation.places.PlayersPlace;
import com.pedrero.eclihand.navigation.places.TeamPlace;
import com.pedrero.eclihand.navigation.places.TeamsPlace;
import com.pedrero.eclihand.navigation.places.WelcomePlace;
import com.pedrero.eclihand.ui.panel.BodyPanel;

@Controller
public class BodyPanelController implements EclihandController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3020127953202830225L;

	@Resource
	private BodyPanel bodyPanel;

	@Resource
	private HomePanelController homePanelController;

	@Resource
	private TeamsPanelController teamsPanelController;

	@Resource
	private TeamPanelController teamPanelController;

	@Resource
	private PlayerPanelController playerPanelController;

	@Resource
	private PlayersPanelController playersPanelController;

	@Resource
	private LeftPanelController leftPanelController;

	@Resource
	private EclihandNavigatorImpl eclihandNavigator;

	@Resource
	private TeamsPlace teamsPlace;

	@Resource
	private TeamPlace teamPlace;

	@Resource
	private PlayersPlace playersPlace;

	@Resource
	private PlayerPlace playerPlace;

	@Resource
	private WelcomePlace welcomePlace;

	public void showTeamsPanel() {
		eclihandNavigator.navigateTo(teamsPlace);
	}

	public void showTeamPanel() {
		eclihandNavigator.navigateTo(teamPlace);
	}

	public void showPlayersPanel() {
		eclihandNavigator.navigateTo(playersPlace);
	}

	public void showPlayerPanel() {
		eclihandNavigator.navigateTo(playerPlace);
	}

	public void showHomePanel() {
		eclihandNavigator.navigateTo(welcomePlace);
	}
}
