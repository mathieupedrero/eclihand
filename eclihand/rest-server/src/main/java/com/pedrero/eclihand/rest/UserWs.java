package com.pedrero.eclihand.rest;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.rest.request.UserCreationRequest;
import com.pedrero.eclihand.service.biz.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserWs extends AbstractEntityWs<UserDto> {

	@Resource
	private UserService userService;

	@Override
	protected UserService getService() {
		return userService;
	}

	@RequestMapping(consumes = IRestWebService.APPLICATION_JSON, method = RequestMethod.POST, value = "/create")
	public void create(@Valid @RequestBody UserCreationRequest request) {
		getService().createUser(request.getUserToCreate(), request.getPasswordToken());
	}

}
