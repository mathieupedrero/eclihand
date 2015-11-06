package com.pedrero.eclihand.converter.in;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.TeamDto;

@Component
public class TeamDtoToTeamImpl implements TeamDtoToTeam {

	@Override
	public Team apply(TeamDto t) {
		return null;
	}
}
