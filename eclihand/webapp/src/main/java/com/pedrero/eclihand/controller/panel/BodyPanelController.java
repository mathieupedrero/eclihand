package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.ui.panel.BodyPanel;
import com.vaadin.ui.Component;

@Controller
public class BodyPanelController implements EclihandController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3020127953202830225L;

	@Resource
	private BodyPanel bodyPanel;
	
	@Resource
	private HomePanelController homePanelController;

	@Resource
	private TeamsPanelController teamsPanelController;

	@Resource
	private TeamPanelController teamPanelController;

	@Resource
	private PlayerPanelController playerPanelController;

	@Resource
	private PlayersPanelController playersPanelController;

	@Resource
	private LeftPanelController leftPanelController;

	@Override
	public void init() {
		leftPanelController.init();

		homePanelController.init();
		teamsPanelController.init();
		teamPanelController.init();
		playerPanelController.init();
		playersPanelController.init();

		bodyPanel.init();
	}

	public void showTeamsPanel(){
		teamsPanelController.display();
		bodyPanel.showTeamsPanel();
	}

	public void showTeamPanel() {
		bodyPanel.showTeamPanel();
	}

	public void showPlayersPanel() {
		bodyPanel.showPlayersPanel();
	}

	public void showPlayerPanel() {
		bodyPanel.showPlayerPanel();
	}

	public void showComponent(Component panel) {
		bodyPanel.showComponent(panel);
	}

	public void showHomePanel() {
		homePanelController.display();
		bodyPanel.showHomePanel();
	}
}
