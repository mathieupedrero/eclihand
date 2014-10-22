package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.PlayerService;

@RestController
@RequestMapping(consumes = "application/json", produces = "application/json", value = "/player")
public class PlayerWs {

	@Resource
	private PlayerService playerService;

	@RequestMapping("/{id}")
	public PlayerDto getPlayer(@PathVariable(value = "id") Long id) {
		return playerService.findById(id);
	}

}
