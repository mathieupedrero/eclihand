package com.pedrero.eclihand.model.domain;

import java.util.Set;

public interface User extends Illustrable {

	String getLogin();

	void setLogin(String login);

	String getMailAddress();

	void setMailAddress(String mailAddress);

	String getPassword();

	void setPassword(String password);

	Set<Profile> getProfiles();

	void setProfiles(Set<Profile> profiles);

	UserType getUserType();

	void setUserType(UserType userType);

}