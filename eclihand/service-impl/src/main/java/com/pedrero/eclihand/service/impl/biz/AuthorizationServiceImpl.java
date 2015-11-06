package com.pedrero.eclihand.service.impl.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.Converter;
import com.pedrero.eclihand.converter.in.AuthorizationDtoToAuthorization;
import com.pedrero.eclihand.converter.out.AuthorizationToAuthorizationDto;
import com.pedrero.eclihand.dao.AuthorizationDao;
import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.dto.AuthorizationDto;
import com.pedrero.eclihand.service.biz.AuthorizationService;

@Service
public class AuthorizationServiceImpl extends DataObjectServiceImpl<AuthorizationDto, Authorization> implements
		AuthorizationService {
	@Resource
	private AuthorizationToAuthorizationDto outPersonConverter;

	@Resource
	private AuthorizationDtoToAuthorization inPersonConverter;

	@Resource
	private AuthorizationDao personDao;

	@Override
	public AuthorizationToAuthorizationDto getOutConverter() {
		return outPersonConverter;
	}

	@Override
	public AuthorizationDao getDao() {
		return personDao;
	}

	@Override
	public Converter<AuthorizationDto, Authorization> getInConverter() {
		return inPersonConverter;
	}

}
