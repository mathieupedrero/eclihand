package com.pedrero.eclihand.model.dto;

import java.util.List;

import com.pedrero.eclihand.model.domain.Gender;

public class TeamDto extends IllustrableDto {
	private Integer year;

	private List<PlayerDto> players;

	private Gender gender;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<PlayerDto> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDto> players) {
		this.players = players;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
