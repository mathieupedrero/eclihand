package com.pedrero.eclihand.service.impl;

import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.PlayerConverter;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.PlayerService;

@SuppressWarnings("rawtypes")
@Service
public class PlayerServiceImpl extends
 DataObjectServiceImpl<PlayerDto, Player>
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

	@Override
	public Integer computeAgeForPlayerWhenPlayingForTeam(Long playerId,
			Long teamId) {
		Team team = teamDao.findById(teamId);
		Player player = playerDao.findById(playerId);
		GregorianCalendar playerBirthDate = new GregorianCalendar();
		playerBirthDate.setTime(player.getPlayerPerson().getBirthDate());
		Integer playerBirthDateYear = playerBirthDate.get(GregorianCalendar.YEAR);
		Integer playerBirthDateMonth = playerBirthDate
				.get(GregorianCalendar.MONTH);
		return team.getYear() - playerBirthDateYear - ( playerBirthDateMonth<GregorianCalendar.SEPTEMBER ? 1 : 2);
	}

}
