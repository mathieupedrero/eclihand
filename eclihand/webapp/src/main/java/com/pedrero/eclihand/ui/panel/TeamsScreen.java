package com.pedrero.eclihand.ui.panel;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.EclihandViewImpl;
import com.pedrero.eclihand.navigation.places.TeamsPlace;
import com.pedrero.eclihand.service.TeamService;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TeamsScreen extends EclihandViewImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(TeamsScreen.class);

	@Resource
	private MessageResolver messageResolver;

	@Resource(name = "teamTableForTeamsPanel")
	private GenericTable<TeamDto> teamTable;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private TeamsPlace teamsPlace;

	@Resource
	private TeamService teamService;

	private Button createNewTeamButton;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954828103989095039L;

	@PostConstruct
	protected void postConstruct() {
		LOGGER.info("initializing TeamsPanel");
		this.setCaption(messageResolver.getMessage("teams.panel.title"));
		Layout layout = eclihandLayoutFactory.createCommonVerticalLayout();

		this.setContent(layout);

		Label label = eclihandUiFactory.createLabel();
		label.setValue("Teams");
		layout.addComponent(label);

		this.createNewTeamButton = eclihandUiFactory.createButton();
		this.createNewTeamButton.setCaption(messageResolver.getMessage("players.create.new"));

		this.createNewTeamButton.addClickListener(new ClickListener() {

			/**
		 *
		 */
			private static final long serialVersionUID = -7117656998497854385L;

			@Override
			public void buttonClick(ClickEvent event) {
				// teamsPanelController.openNewTeamForm();

			}
		});

		layout.addComponent(teamTable);
		layout.addComponent(createNewTeamButton);

		teamTable.feed(teamService.findAll());

		this.setCaption(messageResolver.getMessage("home.caption"));
	}

	public GenericTable<TeamDto> getTeamsTable() {
		return teamTable;
	}

	public void refreshTeams(List<TeamDto> teams) {
		teamTable.removeAllDataObjectsFromTable();
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return teamsPlace;
	}
}
