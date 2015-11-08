package com.pedrero.eclihand.converter.out;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.out.TeamToTeamDto;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.TeamDto;

@Component
public class TeamToTeamDtoImpl implements TeamToTeamDto {

	@Override
	public TeamDto apply(Team t) {
		return null;
	}
}
