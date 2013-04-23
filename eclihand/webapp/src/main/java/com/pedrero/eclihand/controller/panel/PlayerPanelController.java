package com.pedrero.eclihand.controller.panel;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EntityDisplayerController;
import com.pedrero.eclihand.model.dto.PersonDto;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityPanel;
import com.pedrero.eclihand.ui.panel.entity.PlayerPanel;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.UpdatableContentController;

@Controller
public class PlayerPanelController extends AbstractEntityController implements
		EntityDisplayerController<PlayerDto>, UpdatableContentController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private PlayerPanel playerPanel;

	@Resource
	private PlayerService playerService;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private GenericPropertyDisplayer<PlayerDto> playerPropertyDisplayer;

	@Resource(name = "teamTableForPlayerPanel")
	private GenericTable<TeamDto> teamTable;

	private PlayerDto player;

	@Override
	public void init() {

		playerPanel.init();
	}

	public void searchPlayerAndDisplay(Long teamId) {
		PlayerDto entity = playerService.findById(teamId);
		searchPlayerAndDisplay(entity);
		player = entity;
	}

	public void searchPlayerAndDisplay(PlayerDto entity) {
		playerPanel.makeReadOnly();
		playerPanel.display(entity);
	}

	@Override
	public void makeCreateMode() {
		super.makeCreateMode();
		PlayerDto newOne = new PlayerDto();
		newOne.setPlayerPerson(new PersonDto());
		player = newOne;
		playerPanel.makeCreateMode();
		playerPanel.display(newOne);
	}

	@Override
	public void delete() {
		playerService.delete(player);
		bodyPanelController.showHomePanel();
	}

	@Override
	public void display(PlayerDto entity) {
		searchPlayerAndDisplay(entity.getId());
	}

	@Override
	public EntityDisplayerComponent<PlayerDto> getEntityDisplayerComponent() {
		return playerPanel;
	}

	@Override
	public void validateChanges() {
		playerPanel.getPlayerPropertyDisplayer().validateChanges();
		playerPanel.getTeamTable().validateChanges();
		Set<TeamDto> teamList = new HashSet<TeamDto>(playerPanel.getTeamTable()
				.retrieveData());
		player.setTeams(teamList);
		if (player.getId() != null) {
			playerService.update(player);
		} else {
			playerService.save(player);
		}
	}

	@Override
	public AbstractEntityPanel getEntityPanel() {
		return playerPanel;
	}

}
