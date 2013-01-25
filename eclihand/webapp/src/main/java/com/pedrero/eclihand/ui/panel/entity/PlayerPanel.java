package com.pedrero.eclihand.ui.panel.entity;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.PlayerPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.panel.EclihandMainPanel;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component
public class PlayerPanel extends EclihandMainPanel implements
		EntityDisplayerComponent<PlayerDto>,
		Initiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976637745365811486L;

	@Resource
	private PlayerPanelController playerPanelController;

	@Resource
	private GenericPropertyDisplayer<PlayerDto> playerPropertyDisplayer;

	@Resource(name = "teamTable")
	private GenericTable<TeamDto> teamTable;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;

	@Override
	public void display(PlayerDto entity) {

		playerPropertyDisplayer.display(entity);
		teamTable.removeAllDataObjects();
		teamTable.feed(entity.getTeams());
	}

	@Override
	public void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);

		playerPropertyDisplayer.init();
		teamTable.init();

		this.addComponent(playerPropertyDisplayer);
		this.addComponent(teamTable);

	}

}
