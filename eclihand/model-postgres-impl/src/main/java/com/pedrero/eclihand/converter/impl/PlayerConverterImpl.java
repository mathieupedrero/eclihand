package com.pedrero.eclihand.converter.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.converter.PlayerConverter;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.impl.PlayerImpl;
import com.pedrero.eclihand.model.dto.PlayerDto;

@SuppressWarnings("rawtypes")
@Component
public class PlayerConverterImpl extends ConverterImpl<Player, PlayerDto>
		implements PlayerConverter {

	@Override
	public PlayerDto convertToDto(Player domain) {
		return getDozerBeanMapper().map(domain, PlayerDto.class, "player");
	}

	@Override
	public Player convertToEntity(PlayerDto dto) {
		return getDozerBeanMapper().map(dto, PlayerImpl.class, "player");
	}

	@Override
	public void lightFeedEntityWithDto(Player domain, PlayerDto dto) {
		getDozerBeanMapper().map(dto, domain, "player-light");
	}
}
