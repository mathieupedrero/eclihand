package com.pedrero.eclihand.utils.ui.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.utils.ui.UICallback;

@Component
public class PlayerDisplayUICallback implements UICallback<PlayerDto> {

	@Resource
	public PlayerPlace playerPlace;

	@Resource
	public EclihandNavigator navigator;

	@Override
	public void execute(PlayerDto dataObject) {
		playerPlace.setUpdateMode(false);
		playerPlace.setId(dataObject.getId());
		navigator.navigateTo(playerPlace);
	}

}
