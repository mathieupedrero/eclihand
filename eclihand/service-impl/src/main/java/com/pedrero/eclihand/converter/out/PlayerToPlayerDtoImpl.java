package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.dto.PlayerDto;

@Component
public class PlayerToPlayerDtoImpl implements PlayerToPlayerDto {

	@Resource
	private PersonToPersonDto personToPersonDto;

	@Resource
	private TeamToLightTeamDto teamToLightTeamDto;

	@Override
	public PlayerDto apply(Player source) {
		if (source == null) {
			return null;
		}
		PlayerDto result = new PlayerDto();
		map(source::getPlayerPerson, result::setPlayerPerson, personToPersonDto);
		map(source::getTeams, result::setTeams, inSet(teamToLightTeamDto));
		return result;
	}
}
