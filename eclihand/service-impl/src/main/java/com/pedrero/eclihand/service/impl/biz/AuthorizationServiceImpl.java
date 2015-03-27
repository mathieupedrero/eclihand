package com.pedrero.eclihand.service.impl.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pedrero.eclihand.converter.AuthorizationConverter;
import com.pedrero.eclihand.dao.AuthorizationDao;
import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.dto.AuthorizationDto;
import com.pedrero.eclihand.service.biz.AuthorizationService;

@Service
public class AuthorizationServiceImpl extends DataObjectServiceImpl<AuthorizationDto, Authorization> implements
		AuthorizationService {
	@Resource
	private AuthorizationConverter personConverter;

	@Resource
	private AuthorizationDao personDao;

	@Override
	public AuthorizationConverter getConverter() {
		return personConverter;
	}

	public void setPersonConverter(AuthorizationConverter personConverter) {
		this.personConverter = personConverter;
	}

	@Override
	public AuthorizationDao getDao() {
		return personDao;
	}

	public void setPersonDao(AuthorizationDao personDao) {
		this.personDao = personDao;
	}

}
