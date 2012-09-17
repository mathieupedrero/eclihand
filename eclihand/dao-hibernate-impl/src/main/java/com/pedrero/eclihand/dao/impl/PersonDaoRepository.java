package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.PersonDao;
import com.pedrero.eclihand.model.domain.impl.PersonImpl;

@Repository
public interface PersonDaoRepository extends PersonDao<PersonImpl>,
		JpaRepository<PersonImpl, Long> {
	
	public List<PersonImpl> searchByCriterium(String criterium);

}
