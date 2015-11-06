package com.pedrero.eclihand.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.UserDao;
import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.domain.UserImpl;

@Repository
public interface UserDaoRepository extends UserDao, DataObjectRepository<User, UserImpl> {

	@Override
	public List<UserImpl> findByIndexLikeIgnoreCase(String index);

}
