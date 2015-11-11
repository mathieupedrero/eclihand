package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.TeamDto;

@Component
public class TeamToTeamDtoImpl implements TeamToTeamDto {

	@Resource
	private PlayerToLightPlayerDto playerToLightPlayerDto;

	@Override
	public TeamDto apply(Team source) {
		if (source == null) {
			return null;
		}
		TeamDto result = new TeamDto();
		map(source::getYear, result::setYear);
		map(source::getGender, result::setGender);
		map(source::getPlayers, result::setPlayers,
				inSet(playerToLightPlayerDto));
		return result;
	}
}
