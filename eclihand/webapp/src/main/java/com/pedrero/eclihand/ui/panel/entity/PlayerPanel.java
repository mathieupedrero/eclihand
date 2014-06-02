package com.pedrero.eclihand.ui.panel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.ui.EntityDisplayerPanelComponent;
import com.pedrero.eclihand.ui.custom.ViewGenericPropertyDisplayer;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.spring.EclihandBeanFactory;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component(value = "playerPanel")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class PlayerPanel extends AbstractEntityViewPanel implements EntityDisplayerPanelComponent<PlayerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976637745365811486L;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandBeanFactory beanFactory;

	@Resource
	private PlayerPlace playerPlace;

	@Resource
	private PlayerService playerService;

	private Layout layout;

	@Override
	protected void postConstruct() {
		super.postConstruct();

		layout = eclihandLayoutFactory.createCommonVerticalLayout();

		PlayerDto playerToDisplay = playerService.findById(playerPlace.getId());

		ViewGenericPropertyDisplayer<PlayerDto> playerPropertyDisplayer = (ViewGenericPropertyDisplayer<PlayerDto>) beanFactory
				.getBean("playerPropertyDisplayer", playerToDisplay);
		GenericTable<TeamDto> teamTable = (GenericTable<TeamDto>) beanFactory.getBean("teamTableForPlayerPanel",
				EditMode.VIEW);

		teamTable.feed(playerToDisplay.getTeams());

		layout.addComponent(playerPropertyDisplayer);
		layout.addComponent(teamTable);

		this.addComponent(layout);
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return playerPlace;
	}

	@Override
	public Set<Credential> getRequiredCredentials() {
		return new HashSet<Credential>();
	}

}
