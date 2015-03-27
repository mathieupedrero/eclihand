package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.AbstractEclihandView;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.places.TeamsPlace;
import com.pedrero.eclihand.service.biz.TeamService;
import com.pedrero.eclihand.ui.panel.entity.EditMode;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.spring.EclihandBeanFactory;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TeamsScreen extends AbstractEclihandView {
	private static final Logger LOGGER = LoggerFactory.getLogger(TeamsScreen.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954828103989095039L;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private TeamsPlace teamsPlace;

	@Resource
	private TeamService teamService;

	@Resource
	private EclihandBeanFactory beanFactory;

	private Button createNewTeamButton;

	@Override
	protected void postConstruct() {
		super.postConstruct();
		LOGGER.info("initializing TeamsPanel");
		this.getComponent().setCaption(messageResolver.getMessage("teams.panel.title"));

		Label label = eclihandUiFactory.createLabel();
		label.setValue("Teams");
		this.getMainLayout().addComponent(label);

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

		GenericTable<TeamDto> teamTableForTeamPanel = (GenericTable<TeamDto>) beanFactory.getBean(
				"teamTableForTeamsPanel", EditMode.VIEW);

		this.getMainLayout().addComponent(teamTableForTeamPanel.getComponent());
		this.getMainLayout().addComponent(createNewTeamButton);

		teamTableForTeamPanel.feed(teamService.findAll());
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return teamsPlace;
	}
}
