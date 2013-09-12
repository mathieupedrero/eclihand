package com.pedrero.eclihand.ui.panel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamsPanelController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.EclihandViewImpl;
import com.pedrero.eclihand.navigation.places.TeamsPlace;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Layout;

@Component(value = "teamsScreen")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamsScreen extends EclihandViewImpl implements Initiable,
		InitializingBean {
	@Resource
	private MessageResolver messageResolver;

	@Resource
	private TeamsPanelController teamsPanelController;

	@Resource(name = "teamTableForTeamsPanel")
	private GenericTable<TeamDto> teamTable;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private TeamsPlace teamsPlace;

	private Button createNewTeamButton;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954828103989095039L;

	private void init() {
		this.setCaption(messageResolver.getMessage("teams.panel.title"));
		if (layout == null) {
			layout = eclihandLayoutFactory.createCommonVerticalLayout();
		}
		// this.setUiComponent(layout);
		this.setContent(layout);

		this.createNewTeamButton = eclihandUiFactory.createButton();
		this.createNewTeamButton.setCaption(messageResolver
				.getMessage("players.create.new"));

		this.createNewTeamButton.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -7117656998497854385L;

			@Override
			public void buttonClick(ClickEvent event) {
				teamsPanelController.openNewTeamForm();

			}
		});

		layout.addComponent(teamTable);
		layout.addComponent(createNewTeamButton);
	}

	@Override
	public void display() {
		teamsPanelController.searchTeamsAndDisplay();
	}

	public GenericTable<TeamDto> getTeamsTable() {
		return teamTable;
	}

	public void refreshTeams(List<TeamDto> teams) {
		teamTable.removeAllDataObjects();
		teamTable.feed(teams);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return teamsPlace;
	}
}
