package com.pedrero.eclihand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.TeamConverter;
import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Team;
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
	private TeamDao teamDao;

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
