package com.pedrero.eclihand.model.domain.impl;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;

import com.pedrero.eclihand.model.domain.Illustrable;

@Entity
@Table(name = "ILL_ILLUSTRABLE")
@PrimaryKeyJoinColumn(name = "ID")
@Inheritance(strategy = InheritanceType.JOINED)
public class IllustrableImpl extends DataObjectImpl implements Illustrable {

}