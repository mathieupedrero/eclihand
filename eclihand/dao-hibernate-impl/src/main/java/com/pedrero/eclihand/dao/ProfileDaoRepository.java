package com.pedrero.eclihand.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.ProfileDao;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.domain.ProfileImpl;

@Repository
public interface ProfileDaoRepository extends ProfileDao, DataObjectRepository<Profile, ProfileImpl> {

	@Override
	public List<ProfileImpl> findByIndexLikeIgnoreCase(String index);

}
