package com.pedrero.eclihand.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrero.eclihand.model.dto.ProfileDto;
import com.pedrero.eclihand.service.ProfileService;

@RestController
@RequestMapping(consumes = "application/json", produces = "application/json", value = "/profile")
public class ProfileWs extends AbstractWs<ProfileDto> {

	@Resource
	private ProfileService profileService;

	@Override
	protected ProfileService getService() {
		return profileService;
	}

}
