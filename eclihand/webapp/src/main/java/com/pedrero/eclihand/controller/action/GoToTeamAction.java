package com.pedrero.eclihand.controller.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.places.TeamPlace;
import com.pedrero.eclihand.ui.table.config.ITableAction;

@Component
@Scope(value = "prototype")
public class GoToTeamAction implements ITableAction {

	@Resource
	private TeamPlace teamPlace;

	@Resource
	private EclihandNavigator navigator;

	@Override
	public void performAction(Long id) {
		teamPlace.setId(id);
		navigator.navigateTo(teamPlace);
	}

}
