package com.pedrero.eclihand.ui.panel.entity;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.panel.EclihandMainPanel;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
	private GenericTable<PlayerDto> playerTable;

	@Resource
	private TeamPanelController teamPanelController;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

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
	public void display(TeamDto entity) {
		teamPropertyDisplayer.display(entity);
		playerTable.removeAllDataObjects();
		playerTable.feed(entity.getPlayers());
	}

	@Override
	public void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);

		switchUpdateModeButton = eclihandUiFactory.createButton();

		switchUpdateModeButton
				.setCaption(UpdatableContentDisplayer.MAKE_UPDATABLE_KEY);
		switchUpdateModeButton.addListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					teamPanelController.makeReadOnly();
				} else {
					teamPanelController.makeUpdatable();
				}
			}
		});

		validateChanges = eclihandUiFactory.createButton();

		validateChanges
				.setCaption(UpdatableContentDisplayer.VALIDATE_CHANGES_KEY);
		validateChanges.addListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					teamPanelController.validateChanges();
					teamPanelController.makeReadOnly();
				}
			}
		});

		teamPropertyDisplayer.init();
		playerTable.init();

		this.addComponent(teamPropertyDisplayer);
		this.addComponent(playerTable);

		Layout buttonsLayout = eclihandLayoutFactory
				.createCommonHorizontalLayout();
		buttonsLayout.addComponent(switchUpdateModeButton);
		buttonsLayout.addComponent(validateChanges);

		layout.addComponent(buttonsLayout);

	}

}
