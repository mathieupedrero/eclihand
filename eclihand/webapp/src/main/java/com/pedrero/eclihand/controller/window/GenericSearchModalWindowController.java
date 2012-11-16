package com.pedrero.eclihand.controller.window;

import java.util.Collection;
import java.util.List;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.WindowController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.window.GenericSearchModalWindow;
import com.pedrero.eclihand.utils.ui.UICallback;
import com.vaadin.ui.Window;

public abstract class GenericSearchModalWindowController<T extends DataObjectDto>
		implements EclihandController, WindowController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5117454372676672562L;


	public abstract GenericSearchModalWindow<T> getGenericSearchModalWindow();
	
	public abstract DataObjectService<T> getService();

	public abstract UICallback<T> getCallback();

	public abstract WindowController getSuperWindowController();

	@Override
	public void addWindow(Window window) {
		getGenericSearchModalWindow().addWindow(window);
	}

	@Override
	public void init() {
		getGenericSearchModalWindow().init();
	}

	public void openWindow(){
		init();
		getSuperWindowController().addWindow(getGenericSearchModalWindow().getThis());
	}

	public void searchAndDisplay(String criterium) {
		List<T> results = getService().searchByCriterium(criterium);
		getGenericSearchModalWindow().feedTableWith(results);
	}

	public void validateChoice(Collection<T> selection){
		for (T selected : selection) {
			getCallback().execute(selected);
		}
	}
}
