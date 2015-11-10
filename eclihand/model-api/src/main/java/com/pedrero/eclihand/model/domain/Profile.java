package com.pedrero.eclihand.model.domain;

import java.util.Set;

public interface Profile extends Illustrable {

	String getName();

	void setName(String name);

	Set<Authorization> getAuthorizations();

	void setAuthorizations(Set<Authorization> authorizations);

}