package com.pedrero.eclihand.service.impl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.converter.PlayerConverter;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.PlayerService;

@SuppressWarnings("rawtypes")
@Service
public class PlayerServiceImpl extends DataObjectServiceImpl<PlayerDto, Player>
		implements PlayerService {

	@Resource
	private PlayerConverter playerConverter;

	@Resource
	private PlayerDao<Player> playerDao;

	@Resource
	private TeamDao<Team> teamDao;

	@Override
	public PlayerConverter getConverter() {
		return playerConverter;
	}

	public void setPlayerConverter(PlayerConverter playerConverter) {
		this.playerConverter = playerConverter;
	}

	@Override
	public PlayerDao<Player> getDao() {
		return playerDao;
	}

	public void setDao(PlayerDao<Player> playerDao) {
		this.playerDao = playerDao;
	}

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
	public PlayerDto save(PlayerDto dto) {
		PlayerDto saved = super.save(dto);
		Player<Person, Team> player = playerDao.findById(saved.getId());
		for (TeamDto teamDto : dto.getTeams()) {
			Team<Player> team = teamDao.findById(teamDto.getId());
			team.getPlayers().add(player);
			player.getTeams().add(team);
			saved.getTeams().add(teamDto);
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
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public PlayerDto update(PlayerDto dto) {
		Player<Person, Team> player = playerDao.findById(dto.getId());
		for (Team team : player.getTeams()) {
			team.getPlayers().remove(player);
		}
		player.getTeams().clear();
		for (TeamDto teamDto : dto.getTeams()) {
			Team<Player> team = teamDao.findById(teamDto.getId());
			team.getPlayers().add(player);
			player.getTeams().add(team);
		}
		return super.update(dto);
	}

	@Override
	public Integer computeAgeForPlayerWhenPlayingForTeam(Long playerId,
			Long teamId) {
		Team team = teamDao.findById(teamId);
		Player player = playerDao.findById(playerId);
		GregorianCalendar playerBirthDate = new GregorianCalendar();
		playerBirthDate.setTime(player.getPlayerPerson().getBirthDate());
		Integer playerBirthDateYear = playerBirthDate
				.get(GregorianCalendar.YEAR);
		Integer playerBirthDateMonth = playerBirthDate
				.get(GregorianCalendar.MONTH);
		return team.getYear() - playerBirthDateYear
				- (playerBirthDateMonth < GregorianCalendar.SEPTEMBER ? 1 : 2);
	}

	@Override
	@Transactional
	public List<PlayerDto> searchByCriterium(Object criterium) {
		List<PlayerDto> result = new ArrayList<PlayerDto>();
		for (Player player : getDao().findByPlayerPersonIndexLikeIgnoreCase(
				"%" + criterium.toString() + "%")) {
			result.add(getConverter().convertToDto(player));
		}
		return result;
	}

}
