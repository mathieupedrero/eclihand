package com.pedrero.eclihand.dao;

import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.domain.UserType;

public interface UserDao<T extends User> extends DataObjectDao<T> {

	public User findByUserType(UserType userType);

}
