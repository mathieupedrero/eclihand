package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EntityDisplayerPanelController;
import com.pedrero.eclihand.model.dto.PersonDto;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.utils.UpdatableContentController;

@Controller
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class PlayerPanelController extends AbstractEntityController implements
		EntityDisplayerPanelController<PlayerDto>, UpdatableContentController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private PlayerPlace playerPlace;

	@Resource
	private PlayerService playerService;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private GenericPropertyDisplayer<PlayerDto> playerPropertyDisplayer;
	//
	// @Resource(name = "teamTableForPlayerPanel")
	// private GenericTable<TeamDto> teamTable;

	@Resource
	private EclihandNavigator navigator;

	private PlayerDto player;

	@Override
	public void makeCreateMode() {
		super.makeCreateMode();
		PlayerDto newOne = new PlayerDto();
		newOne.setPlayerPerson(new PersonDto());
		player = newOne;
		playerPlace.setUpdateMode(true);
		playerPlace.setId(newOne.getId());
		navigator.navigateTo(playerPlace);
	}

	@Override
	public void delete() {
		playerService.delete(player);
		bodyPanelController.showHomePanel();
	}

	@Override
	public void validateChanges() {
		// playerPanel.getPlayerPropertyDisplayer().validateChanges();
		// playerPanel.getTeamTable().validateChanges();
		// Set<TeamDto> teamList = new
		// HashSet<TeamDto>(playerPanel.getTeamTable()
		// .retrieveData());
		// player.setTeams(teamList);
		// if (player.getId() != null) {
		// playerService.update(player);
		// } else {
		// playerService.save(player);
		// }
	}

	@Override
	public AbstractEntityComponent getEntityPanel() {
		return null;
	}

	@Override
	public PlayerDto giveEntity() {
		return playerService.findById(playerPlace.getId());
	}
}
