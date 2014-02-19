package com.pedrero.eclihand.controller.panel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.TeamService;

@Controller
public class TeamsPanelController implements EclihandController {
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

	public List<TeamDto> searchTeamsToDisplay() {
		return teamService.findAll();
	}

	public void openNewTeamForm() {
		teamPanelController.makeCreateMode();
	}

}
