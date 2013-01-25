package com.pedrero.eclihand.utils.ui.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.utils.IDTOConsumer;
import com.pedrero.eclihand.utils.ui.UICallback;

@Component
public class DTOPickupUICallback<T extends DataObjectDto> implements
		UICallback<T> {

	private IDTOConsumer<T> dtoConsumer;

	@Override
	public void execute(T dataObject) {
		dtoConsumer.consume(dataObject);
	}

	@Override
	public void execute(Iterable<T> dataObjectList) {
		dtoConsumer.consume(dataObjectList);
	}

	public IDTOConsumer<T> getDtoConsumer() {
		return dtoConsumer;
	}

	public void setDtoConsumer(IDTOConsumer<T> dtoConsumer) {
		this.dtoConsumer = dtoConsumer;
	}

}
