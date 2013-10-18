package com.pedrero.eclihand.controller.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamPanelController;
import com.pedrero.eclihand.ui.table.config.ITableAction;

@Component
public class GoToTeamAction implements ITableAction {

	@Resource
	public TeamPanelController teamPanelController;

	@Override
	public void performAction(Long id) {
		teamPanelController.display(id);
	}

}
