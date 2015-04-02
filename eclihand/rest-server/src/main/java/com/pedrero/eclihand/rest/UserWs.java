package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.biz.UserService;

@RestController
@RequestMapping(consumes = IRestWebService.APPLICATION_JSON, produces = IRestWebService.APPLICATION_JSON, value = "/user")
public class UserWs extends AbstractEntityWs<UserDto> {

	@Resource
	private UserService userService;

	@Override
	protected UserService getService() {
		return userService;
	}

}
