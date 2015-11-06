package com.pedrero.eclihand.model.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ILL_ILLUSTRABLE")
@PrimaryKeyJoinColumn(name = "ID")
@Inheritance(strategy = InheritanceType.JOINED)
public class IllustrableImpl extends DataObjectImpl implements Illustrable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7658371494823769938L;

}