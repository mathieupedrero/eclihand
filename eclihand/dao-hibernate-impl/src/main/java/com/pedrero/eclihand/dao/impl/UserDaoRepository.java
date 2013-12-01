package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.UserDao;
import com.pedrero.eclihand.model.domain.impl.UserImpl;

@Repository
public interface UserDaoRepository extends UserDao<UserImpl>,
		JpaRepository<UserImpl, Long> {

	@Override
	public List<UserImpl> findByIndexLikeIgnoreCase(String index);

}
