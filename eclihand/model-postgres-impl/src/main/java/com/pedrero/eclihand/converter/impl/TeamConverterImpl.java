package com.pedrero.eclihand.converter.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.TeamConverter;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.domain.impl.TeamImpl;
import com.pedrero.eclihand.model.dto.TeamDto;

@SuppressWarnings("rawtypes")
@Component
public class TeamConverterImpl extends ConverterImpl<Team, TeamDto> implements
		TeamConverter {

	@Override
	public TeamDto convertToDto(Team domain) {
		return getDozerBeanMapper().map(domain, TeamDto.class, "team");
	}

	@Override
	public Team convertToEntity(TeamDto dto) {
		return getDozerBeanMapper().map(dto, TeamImpl.class, "team");
	}

	@Override
	public void lightFeedEntityWithDto(Team domain, TeamDto dto) {
		getDozerBeanMapper().map(dto, domain, "team-light");
	}
}
