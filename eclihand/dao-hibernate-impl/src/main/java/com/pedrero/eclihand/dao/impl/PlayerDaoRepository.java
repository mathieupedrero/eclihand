package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.model.domain.impl.PersonImpl;
import com.pedrero.eclihand.model.domain.impl.PlayerImpl;

@Repository
public interface PlayerDaoRepository extends PlayerDao<PlayerImpl>,
		JpaRepository<PlayerImpl, Long> {
	
	public List<PlayerImpl> searchByCriterium(String criterium);

}
