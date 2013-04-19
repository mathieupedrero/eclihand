package com.pedrero.eclihand.controller;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.utils.UpdatableContentController;

@Component
public class GenericPropertyDisplayerController<T extends DataObjectDto> implements
 EclihandController, UpdatableContentController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4027324004542068497L;

	private DataObjectService<T> service;

	private GenericPropertyDisplayer<T> genericPropertyDisplayer;

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	public DataObjectService<T> getService() {
		return service;
	}

	public void setService(DataObjectService<T> service) {
		this.service = service;
	}

	@Override
	public void makeUpdatable() {
		getGenericPropertyDisplayer().makeUpdatable();
	}

	@Override
	public void makeCreateMode() {
		getGenericPropertyDisplayer().makeCreateMode();
	}

	@Override
	public void makeReadOnly() {
		getGenericPropertyDisplayer().makeReadOnly();
	}

	@Override
	public void validateChanges() {
		getGenericPropertyDisplayer().validateChanges();
	}

	@Override
	public void delete() {
		// FIXME: Modifier la hierarchie d'interfaces (n'a pas lieu d'être sur
		// un tableau)
	}

	/**
	 * @return the genericPropertyDisplayer
	 */
	public GenericPropertyDisplayer<T> getGenericPropertyDisplayer() {
		return genericPropertyDisplayer;
	}

	/**
	 * @param genericPropertyDisplayer
	 *            the genericPropertyDisplayer to set
	 */
	public void setGenericPropertyDisplayer(GenericPropertyDisplayer<T> genericPropertyDisplayer) {
		this.genericPropertyDisplayer = genericPropertyDisplayer;
	}

}