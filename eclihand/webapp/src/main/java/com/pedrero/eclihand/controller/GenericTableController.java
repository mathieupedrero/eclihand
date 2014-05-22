package com.pedrero.eclihand.controller;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.AbstractEntityController;
import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.utils.IDTOConsumer;
import com.pedrero.eclihand.utils.UpdatableContentController;

@Component
public class GenericTableController<T extends DataObjectDto> extends
		AbstractEntityController implements EclihandController,
		IDTOConsumer<T>, UpdatableContentController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5326406973664925578L;

	public DataObjectService<T> getService() {
		return null;
	}

	public void setService(DataObjectService<T> service) {
	}

	public void openPlayerSearchModalWindow() {
	}

	@Override
	public void consume(T entity) {
		// genericTable.add(entity);
	}

	@Override
	public void consume(Iterable<T> entity) {
		// genericTable.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.utils.UpdatableContentDisplayer#validateChanges()
	 */
	@Override
	public void validateChanges() {
		// getGenericTable().saveData();
	}

	/**
	 * @return the search controller to add entities to the monitored list.
	 */
	public GenericSearchModalWindowController<T> getGenericSearchModalWindowController() {
		return null;
	}

	/**
	 * Sets the of the search controller to add entities to the monitored list.
	 * 
	 * @param genericSearchModalWindowController
	 */
	public void setGenericSearchModalWindowController(
			GenericSearchModalWindowController<T> genericSearchModalWindowController) {
	}

	@Override
	public void delete() {
		// FIXME: Modifier la hierarchie d'interfaces (n'a pas lieu d'être sur
		// un tableau)
	}

	@Override
	public AbstractEntityComponent getEntityPanel() {
		return null;
	}

}
