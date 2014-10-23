package com.pedrero.eclihand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.ProfileConverter;
import com.pedrero.eclihand.dao.ProfileDao;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.dto.ProfileDto;
import com.pedrero.eclihand.service.ProfileService;

@Service
public class ProfileServiceImpl extends DataObjectServiceImpl<ProfileDto, Profile> implements ProfileService {
	@Resource
	private ProfileConverter profileConverter;

	@Resource
	private ProfileDao profileDao;

	@Override
	public ProfileConverter getConverter() {
		return profileConverter;
	}

	public void setProfileConverter(ProfileConverter profileConverter) {
		this.profileConverter = profileConverter;
	}

	@Override
	public ProfileDao getDao() {
		return profileDao;
	}

	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}
}
