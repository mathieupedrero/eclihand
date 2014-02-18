package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.controller.EntityDisplayerPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.places.TeamPlace;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.service.TeamService;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.UpdatableContentController;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamPanelController extends AbstractEntityController implements
		EntityDisplayerPanelController<TeamDto>, UpdatableContentController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private TeamPlace teamPlace;

	@Resource
	private TeamService teamService;

	@Resource
	private PlayerService playerService;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private GenericPropertyDisplayer<TeamDto> teamPropertyDisplayer;

	@Resource
	private GenericTable<PlayerDto> playerTable;

	@Resource
	private EclihandNavigator navigator;

	private TeamDto team;

	@Transactional
	public void display(Long entityId) {
		TeamDto entity = teamService.findTeamToDisplay(entityId);
		team = entity;
		makeReadOnly();
		teamPlace.setId(entityId);
		teamPlace.setUpdateMode(false);
		navigator.navigateTo(teamPlace);
	}

	@Override
	public void makeCreateMode() {
		super.makeCreateMode();
		TeamDto newOne = new TeamDto();
		team = newOne;
		teamPlace.setId(newOne.getId());
		teamPlace.setUpdateMode(false);
		navigator.navigateTo(teamPlace);
	}

	@Override
	public void delete() {
		teamService.delete(team);
		bodyPanelController.showHomePanel();
	}

	@Override
	public void validateChanges() {
		// teamPanel.getTeamPropertyDisplayer().validateChanges();
		// teamPanel.getPlayerTable().validateChanges();
		// List<PlayerDto> teamList = new ArrayList<PlayerDto>(teamPanel
		// .getPlayerTable().retrieveData());
		// team.setPlayers(teamList);
		// if (team.getId() != null) {
		// teamService.update(team);
		// } else {
		// teamService.save(team);
		// }
	}

	@Override
	public AbstractEntityComponent getEntityPanel() {
		return null;
	}

	@Override
	public TeamDto giveEntity() {
		return teamService.findById(teamPlace.getId());
	}
}
