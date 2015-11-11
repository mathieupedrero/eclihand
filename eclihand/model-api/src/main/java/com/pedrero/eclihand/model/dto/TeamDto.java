package com.pedrero.eclihand.model.dto;

import java.util.Set;

import com.pedrero.eclihand.model.domain.Gender;

public class TeamDto extends IllustrableDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7393948532688578828L;

	private Integer year;

	private Set<LightPlayerDto> players;

	private Gender gender;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Set<LightPlayerDto> getPlayers() {
		return players;
	}

	public void setPlayers(Set<LightPlayerDto> players) {
		this.players = players;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
