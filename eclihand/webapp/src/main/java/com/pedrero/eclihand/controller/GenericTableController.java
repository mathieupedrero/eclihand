package com.pedrero.eclihand.controller;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.IDTOConsumer;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;

@Component
public class GenericTableController<T extends DataObjectDto> implements
		EclihandController, IDTOConsumer<T>, UpdatableContentDisplayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5326406973664925578L;

	private GenericTable<T> genericTable;

	private DataObjectService<T> service;

	private GenericSearchModalWindowController<T> genericSearchModalWindowController;

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	public GenericTable<T> getGenericTable() {
		return genericTable;
	}

	public void setGenericTable(GenericTable<T> genericTable) {
		this.genericTable = genericTable;
	}

	public DataObjectService<T> getService() {
		return service;
	}

	public void setService(DataObjectService<T> service) {
		this.service = service;
	}

	public void openPlayerSearchModalWindow() {
		genericSearchModalWindowController.openWindow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.controller.IDTOConsumer#consume(T)
	 */
	@Override
	public void consume(T entity) {
		genericTable.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.controller.IDTOConsumer#consume(java.lang.Iterable)
	 */
	@Override
	public void consume(Iterable<T> entity) {
		genericTable.add(entity);
	}

	@Override
	public void makeUpdatable() {
		getGenericTable().setUpdatable(true);
		getGenericTable().refreshButtonsState();
		getGenericTable().dataTableInit();
		getGenericTable().refreshData();
	}

	@Override
	public void makeReadOnly() {
		getGenericTable().setUpdatable(false);
		getGenericTable().refreshButtonsState();
		getGenericTable().dataTableInit();
		getGenericTable().refreshData();
	}

	@Override
	public void validateChanges() {

		getGenericTable().saveData();
	}

	public GenericSearchModalWindowController<T> getGenericSearchModalWindowController() {
		return genericSearchModalWindowController;
	}

	public void setGenericSearchModalWindowController(
			GenericSearchModalWindowController<T> genericSearchModalWindowController) {
		this.genericSearchModalWindowController = genericSearchModalWindowController;
	}

}
