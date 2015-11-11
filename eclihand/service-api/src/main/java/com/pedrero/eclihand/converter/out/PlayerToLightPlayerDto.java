package com.pedrero.eclihand.converter.out;

import com.pedrero.eclihand.converter.Converter;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.dto.LightPlayerDto;

public interface PlayerToLightPlayerDto extends
		Converter<Player, LightPlayerDto> {

}
