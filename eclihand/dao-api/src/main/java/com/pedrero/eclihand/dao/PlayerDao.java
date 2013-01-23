package com.pedrero.eclihand.dao;

import java.util.List;

import com.pedrero.eclihand.model.domain.Player;

@SuppressWarnings("rawtypes")
public interface PlayerDao<T extends Player> extends DataObjectDao<T> {
	
	public List<T> findByPlayerPersonIndexLikeIgnoreCase(String index);

}
