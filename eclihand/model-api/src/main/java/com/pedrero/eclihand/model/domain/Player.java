package com.pedrero.eclihand.model.domain;

import java.util.Set;

@SuppressWarnings("rawtypes")
public interface Player<P extends Person, T extends Team> extends Illustrable {

	public abstract P getPlayerPerson();

	public abstract void setPlayerPerson(P playerPerson);

	public abstract Set<T> getTeams();

	public abstract void setTeams(Set<T> teams);

}