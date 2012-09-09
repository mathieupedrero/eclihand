package com.pedrero.eclihand.ui;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.utils.EntityDisplayer;

public interface EntityDisplayerComponent<T extends DataObjectDto> extends
		EclihandComponent, EntityDisplayer<T> {
}
