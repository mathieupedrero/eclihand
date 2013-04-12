package com.pedrero.eclihand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.converter.TeamConverter;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.TeamService;

@SuppressWarnings("rawtypes")
@Service
public class TeamServiceImpl extends
 DataObjectServiceImpl<TeamDto, Team>
		implements TeamService {
	@Resource
	private TeamConverter teamConverter;

	@Resource
	private TeamDao<Team> teamDao;

	@Resource
	private PlayerDao<Player> playerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.service.impl.DataObjectServiceImpl#save(com.pedrero
	 * .eclihand.model.dto.DataObjectDto)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public TeamDto save(TeamDto dto) {
		TeamDto saved = super.save(dto);
		Team<Player> team = teamDao.findById(saved.getId());
		for (PlayerDto playerDto : dto.getPlayers()) {
			Player<Person, Team> player = playerDao.findById(playerDto.getId());
			team.getPlayers().add(player);
			player.getTeams().add(team);
			saved.getPlayers().add(playerDto);
		}
		return saved;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.service.impl.DataObjectServiceImpl#update(com.pedrero
	 * .eclihand.model.dto.DataObjectDto)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public TeamDto update(TeamDto dto) {
		Team<Player> team = teamDao.findById(dto.getId());
		for (Player player : team.getPlayers()) {
			player.getTeams().remove(team);
		}
		team.getPlayers().clear();
		for (PlayerDto teamDto : dto.getPlayers()) {
			Player<Person, Team> player = playerDao.findById(teamDto.getId());
			player.getTeams().add(team);
			team.getPlayers().add(player);
		}
		return super.update(dto);
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
