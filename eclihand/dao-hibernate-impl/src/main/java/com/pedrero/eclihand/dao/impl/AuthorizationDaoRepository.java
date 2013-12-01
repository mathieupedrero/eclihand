package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.AuthorizationDao;
import com.pedrero.eclihand.model.domain.impl.AuthorizationImpl;

@Repository
public interface AuthorizationDaoRepository extends
		AuthorizationDao<AuthorizationImpl>,
		JpaRepository<AuthorizationImpl, Long> {

	@Override
	public List<AuthorizationImpl> findByIndexLikeIgnoreCase(String index);

}
