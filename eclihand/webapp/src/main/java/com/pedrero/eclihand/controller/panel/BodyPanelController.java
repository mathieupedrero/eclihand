package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.navigation.EclihandNavigatorImpl;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.navigation.places.PlayersPlace;
import com.pedrero.eclihand.navigation.places.TeamPlace;
import com.pedrero.eclihand.navigation.places.TeamsPlace;
import com.pedrero.eclihand.navigation.places.WelcomePlace;

@Controller
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BodyPanelController implements EclihandController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3020127953202830225L;

	@Resource
	private EclihandNavigatorImpl eclihandNavigator;

	@Resource
	private TeamPlace teamPlace;

	@Resource
	private TeamsPlace teamsPlace;

	@Resource
	private PlayerPlace playerPlace;

	@Resource
	private PlayersPlace playersPlace;

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
