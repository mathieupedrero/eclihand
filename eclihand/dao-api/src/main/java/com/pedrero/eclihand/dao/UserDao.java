package com.pedrero.eclihand.dao;

import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.domain.UserType;

public interface UserDao extends DataObjectDao<User> {

	public User findByUserType(UserType userType);

	public User findByLoginAndPassword(String login, String password);

}
