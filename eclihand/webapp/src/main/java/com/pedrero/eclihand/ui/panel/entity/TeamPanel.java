package com.pedrero.eclihand.ui.panel.entity;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamPanelController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.entity.TeamPropertyDisplayer;
import com.pedrero.eclihand.ui.table.entity.PlayerTable;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Component
public class TeamPanel extends Panel implements
		EntityDisplayerComponent<TeamDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976637745365811486L;

	@Resource
	private TeamPropertyDisplayer teamPropertyDisplayer;

	@Resource
	private PlayerTable playerTable;

	@Resource
	private TeamPanelController teamPanelController;

	private Layout layout;

	@Override
	public void display(TeamDto entity) {
		teamPropertyDisplayer.display(entity);
		playerTable.removeAllDataObjects();
		playerTable.add(entity.getPlayers());
	}

	@Override
	public void init() {
		layout = new VerticalLayout();
		this.setContent(layout);

		teamPropertyDisplayer.init();
		playerTable.init();

		this.addComponent(teamPropertyDisplayer);
		this.addComponent(playerTable);

	}

}
