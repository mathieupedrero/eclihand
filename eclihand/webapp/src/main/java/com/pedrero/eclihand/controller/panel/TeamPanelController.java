package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.EntityDisplayerPanelController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.places.TeamPlace;
import com.pedrero.eclihand.navigation.places.WelcomePlace;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.service.TeamService;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.utils.UpdatableContentController;

@Controller
public class TeamPanelController extends AbstractEntityController implements
		EntityDisplayerPanelController<TeamDto>, UpdatableContentController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private TeamPlace teamPlace;

	@Resource
	private WelcomePlace welcomePlace;

	@Resource
	private TeamService teamService;

	@Resource
	private PlayerService playerService;

	@Resource
	private EclihandNavigator navigator;

	private TeamDto team;

	public void display(Long entityId) {
		TeamDto entity = teamService.findTeamToDisplay(entityId);
		team = entity;
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
		navigator.navigateTo(welcomePlace);
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
