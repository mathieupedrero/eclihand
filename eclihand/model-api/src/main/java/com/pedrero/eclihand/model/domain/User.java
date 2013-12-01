package com.pedrero.eclihand.model.domain;

import java.util.Set;

public interface User extends Illustrable {

	String getLogin();

	String getPassword();

	Set<Profile> getProfiles();

}