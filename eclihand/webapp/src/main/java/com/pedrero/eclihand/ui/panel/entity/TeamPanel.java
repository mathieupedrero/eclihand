package com.pedrero.eclihand.ui.panel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.places.TeamPlace;
import com.pedrero.eclihand.ui.EntityDisplayerPanelComponent;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.UpdatableContentController;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component(value = "teamPanel")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamPanel extends AbstractEntityViewPanel implements
		EntityDisplayerPanelComponent<TeamDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976637745365811486L;

	@Resource
	private GenericPropertyDisplayer<TeamDto> teamPropertyDisplayer;

	@Resource
	private GenericTable<PlayerDto> playerTable;

	@Resource
	private TeamPanelController teamPanelController;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private TeamPlace teamPlace;

	private Layout layout;

	private List<UpdatableContentDisplayer> contentDisplayers;

	/**
	 * @return the teamPropertyDisplayer
	 */
	public GenericPropertyDisplayer<TeamDto> getTeamPropertyDisplayer() {
		return teamPropertyDisplayer;
	}

	/**
	 * @param teamPropertyDisplayer
	 *            the teamPropertyDisplayer to set
	 */
	public void setTeamPropertyDisplayer(
			GenericPropertyDisplayer<TeamDto> teamPropertyDisplayer) {
		this.teamPropertyDisplayer = teamPropertyDisplayer;
	}

	/**
	 * @return the playerTable
	 */
	public GenericTable<PlayerDto> getPlayerTable() {
		return playerTable;
	}

	/**
	 * @param playerTable
	 *            the playerTable to set
	 */
	public void setPlayerTable(GenericTable<PlayerDto> playerTable) {
		this.playerTable = playerTable;
	}

	@Override
	public void display(TeamDto entity) {
		teamPropertyDisplayer.display(entity);
		playerTable.removeAllDataObjects();
		playerTable.feed(entity.getPlayers());
		teamPlace.setId(entity.getId());
	}

	private void init() {
		contentDisplayers = new ArrayList<UpdatableContentDisplayer>();
		contentDisplayers.add(teamPropertyDisplayer);
		contentDisplayers.add(playerTable);

		layout = eclihandLayoutFactory.createCommonVerticalLayout();

		layout.addComponent(teamPropertyDisplayer);
		layout.addComponent(playerTable);
	}

	@Override
	public List<UpdatableContentDisplayer> getContentDisplayers() {
		return contentDisplayers;
	}

	@Override
	public UpdatableContentController getController() {
		return teamPanelController;
	}

	@Override
	public Layout getMainLayout() {
		return layout;
	}

	public void validateChanges() {
	}

	@PostConstruct
	protected void postConstruct() {
		init();
		super.postConstruct();
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return teamPlace;
	}

	@Override
	public void display() {
		this.teamPanelController.display(teamPlace.getId());
	}

}
