package com.pedrero.eclihand.controller.panel;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.controller.EntityDisplayerController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.service.TeamService;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.panel.entity.TeamPanel;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamPanelController implements EntityDisplayerController<TeamDto>,
		UpdatableContentDisplayer {
	public static final String AGE_WHEN_PLAYING_FOR_TEAM = "age.when.playing.for.team";

	/**
	 * 
	 */
	private static final long serialVersionUID = 3448580944349868849L;

	@Resource
	private TeamPanel teamPanel;

	@Resource
	private TeamService teamService;

	@Resource
	private PlayerService playerService;

	@Override
	public void init() {
		teamPanel.init();
	}

	@Transactional
	public void searchTeamAndDisplay(Long teamId) {
		TeamDto team = teamService.findById(teamId);
		for (PlayerDto player : team.getPlayers()) {
			Integer ageWhenPlayingForTeam = playerService
					.computeAgeForPlayerWhenPlayingForTeam(player.getId(),
							team.getId());
			player.getOtherProperties().put(AGE_WHEN_PLAYING_FOR_TEAM,
					ageWhenPlayingForTeam);
		}

		teamPanel.display(team);
	}

	@Override
	@Transactional
	public void display(TeamDto entity) {
		searchTeamAndDisplay(entity.getId());
	}

	@Override
	public EntityDisplayerComponent<TeamDto> getEntityDisplayerComponent() {
		return teamPanel;
	}

	@Override
	public void makeUpdatable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeReadOnly() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateChanges() {
		// TODO Auto-generated method stub

	}

}
