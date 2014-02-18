package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.service.TeamService;
import com.pedrero.eclihand.utils.Displayer;

@Controller
public class TeamsPanelController implements EclihandController, Displayer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private TeamService teamService;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private TeamPanelController teamPanelController;

	public void searchTeamsAndDisplay() {
	}

	@Override
	public void display() {
		bodyPanelController.showTeamsPanel();
	};

	public void openNewTeamForm() {
		teamPanelController.makeCreateMode();
	}

}
