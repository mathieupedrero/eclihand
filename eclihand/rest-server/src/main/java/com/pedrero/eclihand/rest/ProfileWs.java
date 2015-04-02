package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.ProfileDto;
import com.pedrero.eclihand.service.biz.ProfileService;

@RestController
@RequestMapping(consumes = IRestWebService.APPLICATION_JSON, produces = IRestWebService.APPLICATION_JSON, value = "/profile")
public class ProfileWs extends AbstractEntityWs<ProfileDto> {

	@Resource
	private ProfileService profileService;

	@Override
	protected ProfileService getService() {
		return profileService;
	}

}
