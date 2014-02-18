package com.pedrero.eclihand.ui.panel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.AbstractEntityController;
import com.pedrero.eclihand.controller.panel.PlayerPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.ui.EntityDisplayerPanelComponent;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.UpdatableContentController;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component(value = "playerPanel")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlayerPanel extends AbstractEntityViewPanel implements
		EntityDisplayerPanelComponent<PlayerDto>, Initiable {

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

	@Resource
	private PlayerPlace playerPlace;

	private Layout layout;

	private List<UpdatableContentDisplayer> contentDisplayers;

	@Override
	public void display(PlayerDto entity) {
	}

	private void init() {
		contentDisplayers = new ArrayList<UpdatableContentDisplayer>();
		contentDisplayers.add(playerPropertyDisplayer);
		contentDisplayers.add(teamTable);

		layout = eclihandLayoutFactory.createCommonVerticalLayout();

		layout.addComponent(playerPropertyDisplayer);
		layout.addComponent(teamTable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.ui.panel.entity.AbstractEntityPanel#afterPropertiesSet
	 * ()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
		super.afterPropertiesSet();
	}

	/**
	 * @return the playerPanelController
	 */
	public AbstractEntityController getPlayerPanelController() {
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
	public UpdatableContentController getController() {
		return playerPanelController;
	}

	@Override
	public Layout getMainLayout() {
		return layout;
	}

	@Override
	public List<UpdatableContentDisplayer> getContentDisplayers() {
		return contentDisplayers;
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return playerPlace;
	}

	@Override
	public void display() {
		PlayerDto entity = playerPanelController.giveEntity();
		playerPropertyDisplayer.display(entity);
		teamTable.removeAllDataObjects();
		teamTable.feed(entity.getTeams());
	}

}
