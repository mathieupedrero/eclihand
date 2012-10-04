package com.pedrero.eclihand.controller.panel;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.ui.panel.PlayersPanel;
import com.pedrero.eclihand.utils.Displayer;

@Controller
public class PlayersPanelController implements EclihandController, Displayer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private PlayersPanel playersPanel;

	@Resource
	private PlayerService playerService;

	@Override
	public void init() {
		playersPanel.init();
	}

	public void searchTeamsAndDisplay() {
		//playersPanel.getTeamsTable().removeAllItems();
		//List<TeamDto> teams = teamService.findAll();
		//playersPanel.refreshTeams(teams);
	}

	@Override
	public void display() {
		playersPanel.display();
	};

}
