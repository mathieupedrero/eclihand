package com.pedrero.eclihand.utils.ui.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.BodyPanelController;
import com.pedrero.eclihand.controller.panel.PlayerPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.utils.ui.UICallback;

@Component
public class PlayerDisplayUICallback implements UICallback<PlayerDto> {

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private PlayerPanelController playerPanelController;

	@Override
	public void execute(PlayerDto dataObject) {
		// FIXME: fix UICallback
		// bodyPanelController.showComponent(playerPanelController
		// .getEntityDisplayerComponent());
		playerPanelController.display(dataObject.getId());

	}

	@Override
	public void execute(Iterable<PlayerDto> dataObject) {
		throw new RuntimeException("No implementation for multiple selection");

	}

}
