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
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityPanel;
import com.pedrero.eclihand.ui.panel.entity.TeamPanel;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.UpdatableContentController;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamPanelController extends AbstractEntityController implements EntityDisplayerController<TeamDto>,
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

	@Resource
	private GenericPropertyDisplayer<TeamDto> teamPropertyDisplayer;

	@Resource
	private GenericTable<PlayerDto> playerTable;

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

	@Override
	public void makeCreateMode() {
		super.makeCreateMode();
		TeamDto newOne = new TeamDto();
		team = newOne;
		teamPanel.makeCreateMode();
		teamPanel.display(newOne);
	}

	@Override
	public EntityDisplayerComponent<TeamDto> getEntityDisplayerComponent() {
		return teamPanel;
	}

	@Override
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

	@Override
	public AbstractEntityPanel getEntityPanel() {
		return teamPanel;
	}
}
