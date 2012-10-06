package com.pedrero.eclihand.utils.ui;

import com.pedrero.eclihand.model.dto.DataObjectDto;

public interface UICallback<T extends DataObjectDto> {
	public void execute(T dataObject);
}
