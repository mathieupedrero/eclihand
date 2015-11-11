package com.pedrero.eclihand.converter.out;

import static com.pedrero.eclihand.converter.ConverterUtils.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.dto.LightPlayerDto;

@Component
public class PlayerToLightPlayerDtoImpl implements PlayerToLightPlayerDto {

	@Resource
	private PersonToPersonDto personToPersonDto;

	@Override
	public LightPlayerDto apply(Player source) {
		if (source == null) {
			return null;
		}
		LightPlayerDto result = new LightPlayerDto();
		map(source::getPlayerPerson, result::setPlayerPerson, personToPersonDto);
		return result;
	}

}
