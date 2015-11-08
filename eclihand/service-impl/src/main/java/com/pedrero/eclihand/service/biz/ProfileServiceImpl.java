package com.pedrero.eclihand.service.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.in.ProfileDtoToProfile;
import com.pedrero.eclihand.converter.out.ProfileToProfileDto;
import com.pedrero.eclihand.dao.ProfileDao;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.dto.ProfileDto;
import com.pedrero.eclihand.service.biz.ProfileService;

@Service
public class ProfileServiceImpl extends DataObjectServiceImpl<ProfileDto, Profile> implements ProfileService {
	@Resource
	private ProfileToProfileDto outProfileConverter;

	@Resource
	private ProfileDtoToProfile inProfileConverter;

	@Resource
	private ProfileDao profileDao;

	@Override
	public ProfileToProfileDto getOutConverter() {
		return outProfileConverter;
	}

	@Override
	public ProfileDao getDao() {
		return profileDao;
	}

	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	@Override
	public ProfileDtoToProfile getInConverter() {
		return inProfileConverter;
	}
}
