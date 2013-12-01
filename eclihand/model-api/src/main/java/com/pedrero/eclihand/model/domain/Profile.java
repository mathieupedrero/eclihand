package com.pedrero.eclihand.model.domain;

import java.util.Set;

public interface Profile extends Illustrable {

	String getName();

	Set<Authorization> getAuthorizations();

}