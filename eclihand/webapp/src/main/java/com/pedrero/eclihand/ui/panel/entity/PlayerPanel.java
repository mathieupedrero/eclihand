package com.pedrero.eclihand.ui.panel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.PlayerPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.UpdatableContentController;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component
public class PlayerPanel extends AbstractEntityPanel implements
		EntityDisplayerComponent<PlayerDto>, Initiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976637745365811486L;

	@Resource
	private PlayerPanelController playerPanelController;

	@Resource
	private GenericPropertyDisplayer<PlayerDto> playerPropertyDisplayer;

	@Resource(name = "teamTableForPlayerPanel")
	private GenericTable<TeamDto> teamTable;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	private Layout layout;

	private List<UpdatableContentDisplayer> contentDisplayers;

	@Override
	public void display(PlayerDto entity) {
		playerPropertyDisplayer.display(entity);
		teamTable.removeAllDataObjects();
		teamTable.feed(entity.getTeams());
	}

	@Override
	public void init() {
		contentDisplayers = new ArrayList<UpdatableContentDisplayer>();
		contentDisplayers.add(playerPropertyDisplayer);
		contentDisplayers.add(teamTable);

		layout = eclihandLayoutFactory.createCommonVerticalLayout();

		playerPropertyDisplayer.init();
		teamTable.init();

		layout.addComponent(playerPropertyDisplayer);
		layout.addComponent(teamTable);

		super.init();
	}

	/**
	 * @return the playerPanelController
	 */
	public PlayerPanelController getPlayerPanelController() {
		return playerPanelController;
	}

	/**
	 * @param playerPanelController
	 *            the playerPanelController to set
	 */
	public void setPlayerPanelController(
			PlayerPanelController playerPanelController) {
		this.playerPanelController = playerPanelController;
	}

	/**
	 * @return the teamTable
	 */
	public GenericTable<TeamDto> getTeamTable() {
		return teamTable;
	}

	/**
	 * @param teamTable
	 *            the teamTable to set
	 */
	public void setTeamTable(GenericTable<TeamDto> teamTable) {
		this.teamTable = teamTable;
	}

	/**
	 * @return the playerPropertyDisplayer
	 */
	public GenericPropertyDisplayer<PlayerDto> getPlayerPropertyDisplayer() {
		return playerPropertyDisplayer;
	}

	/**
	 * @param playerPropertyDisplayer
	 *            the playerPropertyDisplayer to set
	 */
	public void setPlayerPropertyDisplayer(
			GenericPropertyDisplayer<PlayerDto> playerPropertyDisplayer) {
		this.playerPropertyDisplayer = playerPropertyDisplayer;
	}

	@Override
	public List<UpdatableContentDisplayer> getContentDisplayers() {
		return contentDisplayers;
	}

	@Override
	public UpdatableContentController getController() {
		return playerPanelController;
	}

	@Override
	public Layout getMainLayout() {
		return layout;
	}

}
