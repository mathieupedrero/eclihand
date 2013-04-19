package com.pedrero.eclihand.controller.panel;

import java.util.HashSet;
import java.util.Set;

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
import com.pedrero.eclihand.utils.UpdatableContentController;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamPanelController implements EntityDisplayerController<TeamDto>,
 UpdatableContentController {
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

	@Resource
	private BodyPanelController bodyPanelController;

	private TeamDto team;

	@Override
	public void init() {
		teamPanel.init();
	}

	@Transactional
	public void searchTeamAndDisplay(Long teamId) {
		TeamDto entity = teamService.findById(teamId);
		for (PlayerDto player : entity.getPlayers()) {
			Integer ageWhenPlayingForTeam = playerService
					.computeAgeForPlayerWhenPlayingForTeam(player.getId(),
							entity.getId());
			player.getOtherProperties().put(AGE_WHEN_PLAYING_FOR_TEAM,
					ageWhenPlayingForTeam);
		}

		team = entity;
		teamPanel.makeReadOnly();
		teamPanel.display(entity);
	}

	@Override
	@Transactional
	public void display(TeamDto entity) {
		searchTeamAndDisplay(entity.getId());
	}

	public void createNew() {
		TeamDto newOne = new TeamDto();
		team = newOne;
		teamPanel.display(newOne);
		teamPanel.makeCreateMode();
	}

	@Override
	public EntityDisplayerComponent<TeamDto> getEntityDisplayerComponent() {
		return teamPanel;
	}

	@Override
	public void makeUpdatable() {
		teamPanel.makeUpdatable();
	}

	@Override
	public void makeReadOnly() {
		teamPanel.makeReadOnly();
	}

	@Override
	public void makeCreateMode() {
		teamPanel.makeReadOnly();
	}

	public void delete() {
		teamService.delete(team);
		bodyPanelController.showHomePanel();
	}

	@Override
	public void validateChanges() {
		teamPanel.getTeamPropertyDisplayer().validateChanges();
		teamPanel.getPlayerTable().validateChanges();
		Set<PlayerDto> teamList = new HashSet<PlayerDto>(teamPanel.getPlayerTable().retrieveData());
		team.setPlayers(teamList);
		if (team.getId() != null) {
			teamService.update(team);
		} else {
			teamService.save(team);
		}
	}
}
