package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.PlayerDao;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.impl.PlayerImpl;

@Repository
public interface PlayerDaoRepository extends PlayerDao, DataObjectRepository<Player, PlayerImpl> {

	@Override
	public List<PlayerImpl> findByIndexLikeIgnoreCase(String index);

	@Override
	public List<PlayerImpl> findByPlayerPersonIndexLikeIgnoreCase(String index);

}
