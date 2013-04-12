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
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Layout;

@Component
public class PlayerPanel extends EclihandMainPanel implements
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
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;

	private Boolean updatable;

	/**
	 * Button to switch to update mode
	 */
	private Button switchUpdateModeButton;

	/**
	 * Button to validate changes made in update mode
	 */
	private Button validateChanges;

	@Override
	public void display(PlayerDto entity) {

		playerPropertyDisplayer.display(entity);
		teamTable.removeAllDataObjects();
		teamTable.feed(entity.getTeams());
	}

	@Override
	public void init() {
		updatable = false;
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);

		switchUpdateModeButton = eclihandUiFactory.createButton();

		switchUpdateModeButton
				.setCaption(UpdatableContentDisplayer.MAKE_UPDATABLE_KEY);
		switchUpdateModeButton.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					playerPanelController.makeReadOnly();
				} else {
					playerPanelController.makeUpdatable();
				}
			}
		});

		validateChanges = eclihandUiFactory.createButton();

		validateChanges
				.setCaption(UpdatableContentDisplayer.VALIDATE_CHANGES_KEY);
		validateChanges.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					playerPanelController.validateChanges();
					playerPanelController.makeReadOnly();
				}
			}
		});

		playerPropertyDisplayer.init();
		teamTable.init();

		layout.addComponent(playerPropertyDisplayer);
		layout.addComponent(teamTable);

		Layout buttonsLayout = eclihandLayoutFactory
				.createCommonHorizontalLayout();
		buttonsLayout.addComponent(switchUpdateModeButton);
		buttonsLayout.addComponent(validateChanges);

		layout.addComponent(buttonsLayout);

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
	 * @return the updatable
	 */
	public Boolean getUpdatable() {
		return updatable;
	}

	/**
	 * @param updatable
	 *            the updatable to set
	 */
	public void setUpdatable(Boolean updatable) {
		this.updatable = updatable;
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

}
