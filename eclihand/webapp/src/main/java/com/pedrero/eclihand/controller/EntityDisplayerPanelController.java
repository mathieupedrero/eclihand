package com.pedrero.eclihand.controller;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerPanelComponent;

public interface EntityDisplayerPanelController<T extends DataObjectDto>
		extends EclihandController {
	EntityDisplayerPanelComponent<T> getEntityDisplayerComponent();

	void preparePlace(Long id);

	void display(Long id);

}
