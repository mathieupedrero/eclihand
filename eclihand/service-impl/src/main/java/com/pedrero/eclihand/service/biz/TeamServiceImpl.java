package com.pedrero.eclihand.service.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.Converter;
import com.pedrero.eclihand.converter.in.TeamDtoToTeam;
import com.pedrero.eclihand.converter.out.TeamToTeamDto;
import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.TeamDto;

@Service
public class TeamServiceImpl extends DataObjectServiceImpl<TeamDto, Team>
		implements TeamService {
	public static final String AGE_WHEN_PLAYING_FOR_TEAM = "age.when.playing.for.team";

	@Resource
	private TeamToTeamDto outTeamConverter;

	@Resource
	private TeamDtoToTeam inTeamConverter;

	@Resource
	private TeamDao teamDao;

	@Resource
	private PlayerDao playerDao;

	@Resource
	private PlayerService playerService;

	@Override
	public TeamToTeamDto getOutConverter() {
		return outTeamConverter;
	}

	public void setTeamConverter(TeamToTeamDto teamConverter) {
		this.outTeamConverter = teamConverter;
	}

	@Override
	public TeamDao getDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	@Override
	public Converter<TeamDto, Team> getInConverter() {
		return inTeamConverter;
	}

}
