package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.map;
import static com.pedrero.eclihand.converter.ConverterUtils.setConverter;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.domain.factory.DomainObjectsFactory;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.model.dto.TeamDto;

@Component
public class PlayerDtoToPlayerImpl implements PlayerDtoToPlayer {

	@Resource
	private DomainObjectsFactory domainObjectsFactory;

	@Resource
	private PersonDtoToPerson personDtoToPerson;

	@Resource
	private TeamDao teamDao;

	@Override
	public Player apply(PlayerDto source) {
		if (source == null) {
			return null;
		}
		Player result = domainObjectsFactory.createPlayer();
		map(source::getPlayerPerson, result::setPlayerPerson, personDtoToPerson);
		Function<TeamDto, Team> f = t -> teamDao.findById(t.getId());
		Consumer<Set<Team>> to = result::setTeams;
		map(source::getTeams, result::setTeams,
				setConverter(((TeamDto) t) -> teamDao.findById(t.getId())));
		return result;
	}
}
