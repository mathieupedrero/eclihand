package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.TeamDao;
import com.pedrero.eclihand.model.domain.impl.TeamImpl;

@Repository
public interface TeamDaoRepository extends TeamDao, JpaRepository<TeamImpl, Long> {

	@Override
	public List<TeamImpl> findByIndexLikeIgnoreCase(String index);

}
