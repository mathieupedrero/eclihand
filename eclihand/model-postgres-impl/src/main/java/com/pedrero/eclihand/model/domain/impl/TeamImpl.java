package com.pedrero.eclihand.model.domain.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.Gender;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;

@Entity
@Table(name = "TEA_TEAM")
@PrimaryKeyJoinColumn(name = "ID")
public class TeamImpl extends IllustrableImpl implements Team {

	@Column(name = "TEA_YEAR")
	private Integer year;

	@ManyToMany
	@JoinTable(name = "PLA_PLAYER_TEA_TEAM", joinColumns = @JoinColumn(name = "TEA_ID"), inverseJoinColumns = @JoinColumn(name = "PLA_ID"))
	private final Set<PlayerImpl> players = new HashSet<PlayerImpl>();

	@Column(name = "TEA_GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Override
	public Integer getYear() {
		return year;
	}

	@Override
	public void setYear(Integer year) {
		this.year = year;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Set<Player> getPlayers() {
		return (Set) players;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
