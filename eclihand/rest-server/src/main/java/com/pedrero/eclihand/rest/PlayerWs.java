package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.biz.PlayerService;

@RestController
@RequestMapping(consumes = IRestWebService.APPLICATION_JSON, produces = IRestWebService.APPLICATION_JSON, value = "/player")
public class PlayerWs extends AbstractEntityWs<PlayerDto> {

	@Resource
	private PlayerService playerService;

	@Override
	protected PlayerService getService() {
		return playerService;
	}

}
