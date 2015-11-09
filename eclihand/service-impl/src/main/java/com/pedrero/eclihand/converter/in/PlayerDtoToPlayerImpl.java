package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.fromRepository;
import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;
import static com.pedrero.eclihand.converter.in.InConverterUtils.mapIllustrableFields;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.factory.DomainObjectsFactory;
import com.pedrero.eclihand.model.dto.PlayerDto;

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
		mapIllustrableFields(source, result);
		map(source::getPlayerPerson, result::setPlayerPerson, personDtoToPerson);
		map(source::getTeams, result::setTeams, inSet(fromRepository(teamDao)));
		return result;
	}
}
