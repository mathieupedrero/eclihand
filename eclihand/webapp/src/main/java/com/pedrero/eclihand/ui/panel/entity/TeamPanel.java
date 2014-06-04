package com.pedrero.eclihand.ui.panel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.places.TeamPlace;
import com.pedrero.eclihand.service.TeamService;
import com.pedrero.eclihand.ui.custom.ViewGenericPropertyDisplayer;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.spring.EclihandBeanFactory;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.VerticalLayout;

@Component(value = "teamPanel")
@Scope(value = "prototype")
public class TeamPanel extends AbstractEntityViewPanel {

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandBeanFactory eclihandBeanFactory;

	@Resource
	private TeamPlace teamPlace;

	@Resource
	private TeamService teamService;

	private VerticalLayout layout;

	@Override
	protected void postConstruct() {
		super.postConstruct();

		TeamDto teamToDisplay = teamService.findById(teamPlace.getId());

		ViewGenericPropertyDisplayer<TeamDto> teamPropertyDisplayer = (ViewGenericPropertyDisplayer<TeamDto>) eclihandBeanFactory
				.getBean("teamPropertyDisplayer", teamToDisplay);
		GenericTable<PlayerDto> playerTable = (GenericTable<PlayerDto>) eclihandBeanFactory.getBean("playerTable",
				EditMode.VIEW);
		playerTable.feed(teamToDisplay.getPlayers());

		getMainLayout().addComponent(teamPropertyDisplayer.getWrapperLayout());
		getMainLayout().addComponent(playerTable.getWrapperLayout());

	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return teamPlace;
	}

	@Override
	public Set<Credential> getRequiredCredentials() {
		return new HashSet<Credential>();
	}

}
