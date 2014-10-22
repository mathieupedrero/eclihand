package com.pedrero.eclihand.dao;

import java.util.List;

import com.pedrero.eclihand.model.domain.Player;

public interface PlayerDao extends DataObjectDao<Player> {

	public List<? extends Player> findByPlayerPersonIndexLikeIgnoreCase(String index);

}
