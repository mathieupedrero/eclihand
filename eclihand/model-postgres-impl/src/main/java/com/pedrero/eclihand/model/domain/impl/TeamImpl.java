package com.pedrero.eclihand.model.domain.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.Gender;
import com.pedrero.eclihand.model.domain.Team;

@Entity
@Table(name = "TEA_TEAM")
@NamedNativeQuery(name="TeamImpl.searchByCriterium", query="select * from TEA_TEAM WHERE upper(TEA_TEAM::text) ~ upper(?1)")
public class TeamImpl extends DataObjectImpl implements Team<PlayerImpl> {

	private Integer year;
	
	private List<PlayerImpl> players;

	private Gender gender;

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.Team#getYear()
	 */
	@Override
	@Column(name = "TEA_YEAR")
	public Integer getYear() {
		return year;
	}
	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.Team#setYear(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.model.domain.Team#setYear(java.lang.Integer)
	 */
	@Override
	public void setYear(Integer year) {
		this.year = year;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.model.domain.Team#getPlayers()
	 */
	@Override
	@ManyToMany
	@JoinTable(name = "AFF_PLAYER_TEAM_AFFILIATION", joinColumns = @JoinColumn(name = "AFF_TEA_ID"), inverseJoinColumns = @JoinColumn(name = "AFF_PLA_ID"))
	public List<PlayerImpl> getPlayers() {
		return players;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.model.domain.Team#setPlayers(java.util.List)
	 */
	@Override
	public void setPlayers(List<PlayerImpl> players) {
		this.players = players;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.model.domain.Team#getGender()
	 */
	@Override
	@Column(name = "TEA_GENDER")
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}


}
