package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.PlayerService;

@Controller
public class PlayersPanelController implements EclihandController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private PlayerPanelController playerPanelController;

	@Resource
	private PlayerService playerService;

	@Resource
	private GenericSearchModalWindowController<PlayerDto> playerSearchModalWindowController;

	public void openPlayerSearchModalWindow() {
		playerSearchModalWindowController.openWindow();
	}

	public void openNewPlayerForm() {
		playerPanelController.makeCreateMode();
		bodyPanelController.showPlayerPanel();
	}

}
