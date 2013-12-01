package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.ProfileDao;
import com.pedrero.eclihand.model.domain.impl.ProfileImpl;

@Repository
public interface ProfileDaoRepository extends ProfileDao<ProfileImpl>,
		JpaRepository<ProfileImpl, Long> {

	@Override
	public List<ProfileImpl> findByIndexLikeIgnoreCase(String index);

}
