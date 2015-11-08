package com.pedrero.eclihand.model.domain;

import java.util.Set;

public interface Player extends Illustrable {

	Person getPlayerPerson();

	void setPlayerPerson(Person playerPerson);

	Set<Team> getTeams();

	void setTeams(Set<Team> teams);

}