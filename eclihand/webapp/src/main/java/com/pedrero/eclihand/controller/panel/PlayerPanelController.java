package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EntityDisplayerController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.panel.entity.PlayerPanel;

@Controller
public class PlayerPanelController implements
		EntityDisplayerController<PlayerDto> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private PlayerPanel playerPanel;

	@Resource
	private PlayerService playerService;

	@Override
	public void init() {
		playerPanel.init();
	}

	public void searchTeamAndDisplay(Long teamId) {
		PlayerDto team = playerService.findById(teamId);
		playerPanel.display(team);
	}

	@Override
	public void display(PlayerDto entity) {
		searchTeamAndDisplay(entity.getId());
	}

	@Override
	public EntityDisplayerComponent<PlayerDto> getEntityDisplayerComponent() {
		return playerPanel;
	}

}