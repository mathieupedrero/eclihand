package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.map;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.dto.LightTeamDto;

@Component
public class TeamToLightTeamDtoImpl implements TeamToLightTeamDto {

	@Override
	public LightTeamDto apply(Team source) {
		if (source == null) {
			return null;
		}
		LightTeamDto result = new LightTeamDto();
		map(source::getYear, result::setYear);
		map(source::getGender, result::setGender);
		return result;
	}

}
