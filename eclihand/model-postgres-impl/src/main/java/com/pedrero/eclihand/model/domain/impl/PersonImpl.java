package com.pedrero.eclihand.model.domain.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pedrero.eclihand.model.domain.Gender;
import com.pedrero.eclihand.model.domain.Person;

@Entity
@Table(name = "PER_PERSON")
@NamedNativeQuery(name="PersonImpl.searchByCriterium", query="select * from PER_PERSON WHERE upper(PER_PERSON::text) ~ upper(?1)")
public class PersonImpl extends DataObjectImpl implements Person {
	private String firstName;

	private String lastName;

	private Date birthDate;

	private Gender gender;

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#getFirstName()
	 */
	@Override
	@Column(name = "PER_FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#getLastName()
	 */
	@Override
	@Column(name = "PER_LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#getBirthDate()
	 */
	@Override
	@Column(name = "PER_BIRTH_DATE")
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#setBirthDate(java.util.Date)
	 */
	@Override
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#getGender()
	 */
	@Override
	@Column(name = "PER_GENDER")
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	/* (non-Javadoc)
	 * @see com.pedrero.eclihand.model.domain.impl.Person#setGender(com.pedrero.eclihand.model.domain.Gender)
	 */
	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
