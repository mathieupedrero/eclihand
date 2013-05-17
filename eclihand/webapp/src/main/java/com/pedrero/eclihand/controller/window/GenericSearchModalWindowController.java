package com.pedrero.eclihand.controller.window;

import java.util.Collection;
import java.util.List;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.WindowController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.window.GenericSearchModalWindow;
import com.pedrero.eclihand.utils.ui.UICallback;
import com.pedrero.eclihand.utils.ui.worker.AsynchronousUIWorker;
import com.pedrero.eclihand.utils.ui.worker.UIAction;

public class GenericSearchModalWindowController<T extends DataObjectDto>
		implements EclihandController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5117454372676672562L;

	private GenericSearchModalWindow<T> genericSearchModalWindow;

	private DataObjectService<T> service;

	private UICallback<T> callback;

	private WindowController superWindowController;

	public void openWindow() {
		getSuperWindowController().addWindow(getGenericSearchModalWindow());
	}

	public void searchAndDisplay(String criterium) {
		getGenericSearchModalWindow().getProgressIndicator().setEnabled(true);
		getGenericSearchModalWindow().getProgressIndicator().setVisible(true);
		UIAction action = new SearchAndDisplayUIAction(criterium);
		AsynchronousUIWorker worker = new AsynchronousUIWorker();
		worker.setAction(action);
		worker.start();
	}

	public void validateChoice(Collection<T> selection) {
		for (T selected : selection) {
			getCallback().execute(selected);
		}
	}

	private class SearchAndDisplayUIAction implements UIAction {

		public SearchAndDisplayUIAction(String criterium) {
			super();
			this.criterium = criterium;
		}

		private String criterium;

		@Override
		public void run() {
			List<T> results = getService().searchByCriterium(criterium);
			synchronized (getGenericSearchModalWindow().getUI()) {
				getGenericSearchModalWindow().feedTableWith(results);
				getGenericSearchModalWindow().getProgressIndicator()
						.setVisible(false);
				getGenericSearchModalWindow().getProgressIndicator()
						.setEnabled(false);
			}

		}

	}

	public GenericSearchModalWindow<T> getGenericSearchModalWindow() {
		return genericSearchModalWindow;
	}

	public void setGenericSearchModalWindow(
			GenericSearchModalWindow<T> genericSearchModalWindow) {
		this.genericSearchModalWindow = genericSearchModalWindow;
	}

	public DataObjectService<T> getService() {
		return service;
	}

	public void setService(DataObjectService<T> service) {
		this.service = service;
	}

	public UICallback<T> getCallback() {
		return callback;
	}

	public void setCallback(UICallback<T> callback) {
		this.callback = callback;
	}

	public WindowController getSuperWindowController() {
		return superWindowController;
	}

	public void setSuperWindowController(WindowController superWindowController) {
		this.superWindowController = superWindowController;
	}
}
