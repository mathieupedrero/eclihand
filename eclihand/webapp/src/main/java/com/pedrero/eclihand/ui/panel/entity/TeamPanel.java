package com.pedrero.eclihand.ui.panel.entity;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamPanelController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.panel.EclihandMainPanel;
import com.pedrero.eclihand.ui.table.entity.PlayerTable;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component
public class TeamPanel extends EclihandMainPanel implements
		EntityDisplayerComponent<TeamDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976637745365811486L;

	@Resource
	private GenericPropertyDisplayer<TeamDto> teamPropertyDisplayer;

	@Resource
	private PlayerTable playerTable;

	@Resource
	private TeamPanelController teamPanelController;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;

	@Override
	public void display(TeamDto entity) {
		teamPropertyDisplayer.display(entity);
		playerTable.removeAllDataObjects();
		playerTable.add(entity.getPlayers());
	}

	@Override
	public void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);

		teamPropertyDisplayer.init();
		playerTable.init();

		this.addComponent(teamPropertyDisplayer);
		this.addComponent(playerTable);

	}

}
