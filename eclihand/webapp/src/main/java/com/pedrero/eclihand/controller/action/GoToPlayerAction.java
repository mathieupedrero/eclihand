package com.pedrero.eclihand.controller.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.ui.table.config.ITableAction;

@Component
public class GoToPlayerAction implements ITableAction {

	@Resource
	public PlayerPlace playerPlace;

	@Resource
	public EclihandNavigator navigator;

	@Override
	public void performAction(Long id) {
		playerPlace.setUpdateMode(false);
		playerPlace.setId(id);
		navigator.navigateTo(playerPlace);
	}

}
