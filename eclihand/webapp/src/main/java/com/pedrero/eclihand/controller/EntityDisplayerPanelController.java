package com.pedrero.eclihand.controller;

import com.pedrero.eclihand.model.dto.DataObjectDto;

public interface EntityDisplayerPanelController<T extends DataObjectDto>
		extends EclihandController {
	T giveEntity();
}
