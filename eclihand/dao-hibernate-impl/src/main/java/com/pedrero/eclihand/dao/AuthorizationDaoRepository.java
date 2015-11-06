package com.pedrero.eclihand.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.AuthorizationDao;
import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.AuthorizationImpl;

@Repository
public interface AuthorizationDaoRepository extends AuthorizationDao,
		DataObjectRepository<Authorization, AuthorizationImpl> {

	@Override
	public List<AuthorizationImpl> findByIndexLikeIgnoreCase(String index);

}
