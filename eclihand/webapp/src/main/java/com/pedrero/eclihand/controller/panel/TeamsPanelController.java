package com.pedrero.eclihand.controller.panel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.TeamService;
import com.pedrero.eclihand.ui.panel.TeamsPanel;
import com.pedrero.eclihand.utils.Displayer;

@Controller
public class TeamsPanelController implements EclihandController, Displayer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private TeamsPanel teamsPanel;

	@Resource
	private TeamService teamService;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private TeamPanelController teamPanelController;

	@Override
	public void init() {
		teamsPanel.init();
	}

	public void searchTeamsAndDisplay() {
		teamsPanel.getTeamsTable().removeAllDataObjects();
		List<TeamDto> teams = teamService.findAll();
		teamsPanel.refreshTeams(teams);
	}

	@Override
	public void display() {
		teamsPanel.display();
	};

	public void openNewTeamForm() {
		bodyPanelController.showTeamPanel();
		teamPanelController.makeCreateMode();
	}

}
