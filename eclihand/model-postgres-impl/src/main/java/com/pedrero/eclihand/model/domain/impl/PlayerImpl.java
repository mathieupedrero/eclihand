package com.pedrero.eclihand.model.domain.impl;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.Player;

@Entity
@Table(name = "PLA_PLAYER")
@PrimaryKeyJoinColumn(name = "ID")
@NamedNativeQuery(name = "PlayerImpl.searchByCriterium", query = "select pla.* from PLA_PLAYER pla LEFT JOIN PER_PERSON per ON per.id = pla.pla_per_id WHERE upper(cast(pla as text)) ~ upper(?1) OR upper(cast(per as text)) ~ upper(?1)", resultClass = PlayerImpl.class)
public class PlayerImpl extends DataObjectImpl implements
		Player<PersonImpl, TeamImpl> {

	private PersonImpl playerPerson;

	private List<TeamImpl> teams;

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Player#getPlayerPerson()
	 */
	@Override
	@OneToOne
	@JoinColumn(name = "PLA_PER_ID")
	public PersonImpl getPlayerPerson() {
		return playerPerson;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Player#setPlayerPerson(com.pedrero.eclihand.model.domain.impl.PersonImpl)
	 */
	@Override
	public void setPlayerPerson(PersonImpl playerPerson) {
		this.playerPerson = playerPerson;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Player#getTeams()
	 */
	@Override
	@ManyToMany(mappedBy = "players")
	public List<TeamImpl> getTeams() {
		return teams;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Player#setTeams(java.util.List)
	 */
	@Override
	public void setTeams(List<TeamImpl> teams) {
		this.teams = teams;
	}

}
