package com.pedrero.eclihand.converter.in;

import static com.pedrero.eclihand.converter.ConverterUtils.fromRepository;
import static com.pedrero.eclihand.converter.ConverterUtils.inSet;
import static com.pedrero.eclihand.converter.ConverterUtils.map;
import static com.pedrero.eclihand.converter.in.InConverterUtils.mapIllustrableFields;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.domain.factory.DomainObjectsFactory;
import com.pedrero.eclihand.model.dto.TeamDto;

@Component
public class TeamDtoToTeamImpl implements TeamDtoToTeam {

	@Resource
	private DomainObjectsFactory domainObjectsFactory;

	@Resource
	private PlayerDao playerDao;

	@Override
	public Team apply(TeamDto source) {
		if (source == null) {
			return null;
		}
		Team result = domainObjectsFactory.createTeam();
		mapIllustrableFields(source, result);
		map(source::getYear, result::setYear);
		map(source::getGender, result::setGender);
		map(source::getPlayers, result::setPlayers, inSet(fromRepository(playerDao)));
		return result;
	}
}
