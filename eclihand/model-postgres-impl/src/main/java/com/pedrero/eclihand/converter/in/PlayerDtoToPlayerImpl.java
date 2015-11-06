package com.pedrero.eclihand.converter.in;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.dto.PlayerDto;

@Component
public class PlayerDtoToPlayerImpl implements PlayerDtoToPlayer {

	@Override
	public Player apply(PlayerDto t) {
		return null;
	}
}
