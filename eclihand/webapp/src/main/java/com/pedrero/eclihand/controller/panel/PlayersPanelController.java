package com.pedrero.eclihand.controller.panel;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.ui.panel.PlayersScreen;

@Controller
public class PlayersPanelController implements EclihandController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private PlayersScreen playersPanel;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private PlayerPanelController playerPanelController;

	@Resource
	private PlayerService playerService;

	@Resource
	private GenericSearchModalWindowController<PlayerDto> playerSearchModalWindowController;

	@Override
	public void init() {
		playersPanel.init();
	}

	public void openPlayerSearchModalWindow() {
		playerSearchModalWindowController.openWindow();
	}

	public void openNewPlayerForm() {
		bodyPanelController.showPlayerPanel();
		playerPanelController.makeCreateMode();
	}

}
