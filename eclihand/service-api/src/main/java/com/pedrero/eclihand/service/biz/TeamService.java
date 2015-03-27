package com.pedrero.eclihand.service.biz;

import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.common.DataObjectService;

public interface TeamService extends DataObjectService<TeamDto> {

	TeamDto findTeamToDisplay(Long id);

}
