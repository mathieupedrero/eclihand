package com.pedrero.eclihand.service;

import com.pedrero.eclihand.model.dto.PlayerDto;

public interface PlayerService extends DataObjectService<PlayerDto> {

	public Integer computeAgeForPlayerWhenPlayingForTeam(Long playerId,
			Long teamId);

}
