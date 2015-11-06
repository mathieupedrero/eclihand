package com.pedrero.eclihand.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.domain.TeamImpl;

@Repository
public interface TeamDaoRepository extends TeamDao, DataObjectRepository<Team, TeamImpl> {

	@Override
	public List<TeamImpl> findByIndexLikeIgnoreCase(String index);

}
