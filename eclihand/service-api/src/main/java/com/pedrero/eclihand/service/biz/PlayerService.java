package com.pedrero.eclihand.service.biz;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.common.DataObjectService;

public interface PlayerService extends DataObjectService<PlayerDto> {

	public Integer computeAgeForPlayerWhenPlayingForTeam(Long playerId,
			Long teamId);

}
