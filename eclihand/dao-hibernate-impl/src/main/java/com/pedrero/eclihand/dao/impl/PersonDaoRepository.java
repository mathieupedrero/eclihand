package com.pedrero.eclihand.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pedrero.eclihand.dao.PersonDao;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.impl.PersonImpl;

@Repository
public interface PersonDaoRepository extends PersonDao, DataObjectRepository<Person, PersonImpl> {

	@Override
	public List<PersonImpl> findByIndexLikeIgnoreCase(String index);

}
