package com.pedrero.eclihand.service;

import com.pedrero.eclihand.model.dto.TeamDto;

public interface TeamService extends DataObjectService<TeamDto> {

	TeamDto findTeamToDisplay(Long id);

}
