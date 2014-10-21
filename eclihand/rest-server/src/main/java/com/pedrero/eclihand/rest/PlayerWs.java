package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.PlayerService;

@RestController
public class PlayerWs {

	@Resource
	private PlayerService playerService;

	@RequestMapping("/player/{id}")
	public PlayerDto getPlayer(@RequestParam(value = "id") Long name) {
		return playerService.findById(name);
	}

}
