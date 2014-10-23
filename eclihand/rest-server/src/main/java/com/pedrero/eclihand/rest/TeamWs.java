package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.service.TeamService;

@RestController
@RequestMapping(consumes = "application/json", produces = "application/json", value = "/team")
public class TeamWs extends AbstractWs<TeamDto> {

	@Resource
	private TeamService teamService;

	@Override
	protected TeamService getService() {
		return teamService;
	}

	@Override
	public TeamDto findById(@PathVariable(value = "id") Long id) {
		return teamService.findTeamToDisplay(id);
	}

}
