package com.pedrero.eclihand.service.biz;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrero.eclihand.converter.Converter;
import com.pedrero.eclihand.converter.in.PersonDtoToPerson;
import com.pedrero.eclihand.converter.in.PlayerDtoToPlayer;
import com.pedrero.eclihand.converter.out.PersonToPersonDto;
import com.pedrero.eclihand.converter.out.PlayerToPlayerDto;
import com.pedrero.eclihand.dao.PersonDao;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.biz.PlayerService;

@Service
public class PlayerServiceImpl extends DataObjectServiceImpl<PlayerDto, Player> implements PlayerService {

	@Resource
	private PlayerToPlayerDto outPlayerConverter;

	@Resource
	private PlayerDtoToPlayer inPlayerConverter;

	@Resource
	private PersonToPersonDto outPersonConverter;

	@Resource
	private PersonDtoToPerson inPersonConverter;

	@Resource
	private PlayerDao playerDao;

	@Resource
	private PersonDao personDao;

	@Resource
	private TeamDao teamDao;

	@Override
	public PlayerToPlayerDto getOutConverter() {
		return outPlayerConverter;
	}

	public void setPlayerConverter(PlayerToPlayerDto playerConverter) {
		this.outPlayerConverter = playerConverter;
	}

	@Override
	public PlayerDao getDao() {
		return playerDao;
	}

	public void setDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
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
			result.add(getOutConverter().apply(player));
		}
		return result;
	}

	@Override
	public Converter<PlayerDto, Player> getInConverter() {
		return inPlayerConverter;
	}

}
