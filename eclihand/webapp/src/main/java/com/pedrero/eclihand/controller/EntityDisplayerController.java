package com.pedrero.eclihand.controller;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.utils.EntityDisplayer;

public interface EntityDisplayerController<T extends DataObjectDto> extends
		EntityDisplayer<T>, EclihandController {
	public EntityDisplayerComponent<T> getEntityDisplayerComponent();

}
