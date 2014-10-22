package com.pedrero.eclihand.service.impl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.converter.PersonConverter;
import com.pedrero.eclihand.converter.PlayerConverter;
import com.pedrero.eclihand.dao.PersonDao;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.PlayerService;

@Service
public class PlayerServiceImpl extends DataObjectServiceImpl<PlayerDto, Player> implements PlayerService {

	@Resource
	private PlayerConverter playerConverter;

	@Resource
	private PersonConverter personConverter;

	@Resource
	private PlayerDao playerDao;

	@Resource
	private PersonDao personDao;

	@Resource
	private TeamDao teamDao;

	@Override
	public PlayerConverter getConverter() {
		return playerConverter;
	}

	public void setPlayerConverter(PlayerConverter playerConverter) {
		this.playerConverter = playerConverter;
	}

	@Override
	public PlayerDao getDao() {
		return playerDao;
	}

	public void setDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	@Override
	@Transactional
	public PlayerDto save(PlayerDto dto) {
		Player domain = getConverter().convertToEntity(dto);
		Player saved = getDao().save(domain);
		Person playerPerson;
		if (dto.getId() != null) {
			playerPerson = personDao.findById(dto.getId());
			personConverter.lightFeedEntityWithDto(playerPerson, dto.getPlayerPerson());
		} else {
			playerPerson = personConverter.convertToEntity(dto.getPlayerPerson());
			personDao.save(playerPerson);
		}
		saved.setPlayerPerson(playerPerson);
		Player player = playerDao.findById(saved.getId());
		for (TeamDto teamDto : dto.getTeams()) {
			Team team = teamDao.findById(teamDto.getId());
			team.getPlayers().add(player);
			player.getTeams().add(team);
		}
		return playerConverter.convertToDto(saved);
	}

	@Override
	@Transactional
	public PlayerDto update(PlayerDto dto) {
		Player player = playerDao.findById(dto.getId());
		for (Team team : player.getTeams()) {
			team.getPlayers().remove(player);
		}
		player.getTeams().clear();
		for (TeamDto teamDto : dto.getTeams()) {
			Team team = teamDao.findById(teamDto.getId());
			team.getPlayers().add(player);
			player.getTeams().add(team);
		}
		return super.update(dto);
	}

	@Override
	public Integer computeAgeForPlayerWhenPlayingForTeam(Long playerId, Long teamId) {
		Team team = teamDao.findById(teamId);
		Player player = playerDao.findById(playerId);
		GregorianCalendar playerBirthDate = new GregorianCalendar();
		playerBirthDate.setTime(player.getPlayerPerson().getBirthDate());
		Integer playerBirthDateYear = playerBirthDate.get(GregorianCalendar.YEAR);
		Integer playerBirthDateMonth = playerBirthDate.get(GregorianCalendar.MONTH);
		return team.getYear() - playerBirthDateYear - (playerBirthDateMonth < GregorianCalendar.SEPTEMBER ? 1 : 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.service.impl.DataObjectServiceImpl#delete(com.pedrero
	 * .eclihand.model.dto.DataObjectDto)
	 */
	@Override
	@Transactional
	public void delete(PlayerDto dto) {
		Player toDelete = playerDao.findById(dto.getId());
		for (Team team : toDelete.getTeams()) {
			team.getPlayers().remove(toDelete);
		}
		toDelete.getTeams().clear();
		playerDao.delete(toDelete);
	}

	@Override
	@Transactional
	public List<PlayerDto> searchByCriterium(Object criterium) {
		List<PlayerDto> result = new ArrayList<PlayerDto>();
		for (Player player : getDao().findByPlayerPersonIndexLikeIgnoreCase("%" + criterium.toString() + "%")) {
			result.add(getConverter().convertToDto(player));
		}
		return result;
	}

}
