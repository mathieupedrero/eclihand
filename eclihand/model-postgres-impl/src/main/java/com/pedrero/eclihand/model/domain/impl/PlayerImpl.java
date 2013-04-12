package com.pedrero.eclihand.model.domain.impl;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;

@Entity
@Table(name = "PLA_PLAYER")
@PrimaryKeyJoinColumn(name = "ID")
public class PlayerImpl extends IllustrableImpl implements
 Player {

	@OneToOne
	@JoinColumn(name = "PLA_PER_ID")
	private PersonImpl playerPerson;

	@ManyToMany(mappedBy = "players")
	private Set<TeamImpl> teams;

	@Override
	public PersonImpl getPlayerPerson() {
		return playerPerson;
	}

	@Override
	public void setPlayerPerson(Person playerPerson) {
		this.playerPerson = (PersonImpl) playerPerson;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Set<Team> getTeams() {
		return (Set) this.teams;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTeams(Set<Team> teams) {
		this.teams = (Set) teams;
	}

}
