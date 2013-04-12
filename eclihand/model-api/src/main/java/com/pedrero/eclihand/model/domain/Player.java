package com.pedrero.eclihand.model.domain;

import java.util.Set;

public interface Player extends Illustrable {

	public abstract Person getPlayerPerson();

	public abstract void setPlayerPerson(Person playerPerson);

	public abstract Set<Team> getTeams();

	public abstract void setTeams(Set<Team> teams);

}