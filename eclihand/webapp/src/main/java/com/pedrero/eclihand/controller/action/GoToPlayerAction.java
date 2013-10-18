package com.pedrero.eclihand.controller.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.PlayerPanelController;
import com.pedrero.eclihand.ui.table.config.ITableAction;

@Component
public class GoToPlayerAction implements ITableAction {

	@Resource
	public PlayerPanelController playerPanelController;

	@Override
	public void performAction(Long id) {
		playerPanelController.display(id);

	}

}
