package com.pedrero.eclihand.ui;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.navigation.EclihandView;

public interface EntityDisplayerPanelComponent<T extends DataObjectDto> extends
		EntityDisplayerComponent<T>, EclihandView {
}
