package com.pedrero.eclihand.controller;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;

public interface EntityDisplayerController<T extends DataObjectDto> extends EclihandController {
	public EntityDisplayerComponent<T> getEntityDisplayerComponent();

	public abstract void display(Long id);

}
