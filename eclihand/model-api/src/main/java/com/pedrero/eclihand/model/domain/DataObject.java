package com.pedrero.eclihand.model.domain;

import java.io.Serializable;

public interface DataObject extends Serializable {

	public abstract Long getId();

	public abstract void setId(Long id);

	public String getIndex();

	public void setIndex(String index);

}