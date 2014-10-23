package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.UserService;

@RestController
@RequestMapping(consumes = "application/json", produces = "application/json", value = "/user")
public class UserWs extends AbstractWs<UserDto> {

	@Resource
	private UserService userService;

	@Override
	protected UserService getService() {
		return userService;
	}

}
