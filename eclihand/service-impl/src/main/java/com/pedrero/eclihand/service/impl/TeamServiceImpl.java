package com.pedrero.eclihand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.converter.TeamConverter;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.service.TeamService;

@Service
public class TeamServiceImpl extends DataObjectServiceImpl<TeamDto, Team> implements TeamService {
	public static final String AGE_WHEN_PLAYING_FOR_TEAM = "age.when.playing.for.team";

	@Resource
	private TeamConverter teamConverter;

	@Resource
	private TeamDao teamDao;

	@Resource
	private PlayerDao playerDao;

	@Resource
	private PlayerService playerService;

	@Override
	@Transactional
	public TeamDto update(TeamDto dto) {
		Team team = teamDao.findById(dto.getId());
		for (Player player : team.getPlayers()) {
			player.getTeams().remove(team);
		}
		team.getPlayers().clear();
		for (PlayerDto teamDto : dto.getPlayers()) {
			Player player = playerDao.findById(teamDto.getId());
			player.getTeams().add(team);
			team.getPlayers().add(player);
		}
		return super.update(dto);
	}

	@Override
	@Transactional
	public void delete(TeamDto dto) {
		Team toDelete = teamDao.findById(dto.getId());
		for (Player player : toDelete.getPlayers()) {
			player.getTeams().remove(toDelete);
		}
		toDelete.getPlayers().clear();
		teamDao.delete(toDelete);
	}

	@Override
	@Transactional
	public TeamDto findTeamToDisplay(Long id) {
		Team entity = teamDao.findById(id);
		TeamDto toReturn = teamConverter.convertToDto(entity);
		for (PlayerDto player : toReturn.getPlayers()) {
			Integer ageWhenPlayingForTeam = playerService.computeAgeForPlayerWhenPlayingForTeam(player.getId(),
					entity.getId());
			player.getOtherProperties().put(AGE_WHEN_PLAYING_FOR_TEAM, ageWhenPlayingForTeam);
		}
		return toReturn;
	}

	@Override
	public TeamConverter getConverter() {
		return teamConverter;
	}

	public void setTeamConverter(TeamConverter teamConverter) {
		this.teamConverter = teamConverter;
	}

	@Override
	public TeamDao getDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

}
