package com.pedrero.eclihand.controller.window;

import java.util.Collection;
import java.util.List;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.pedrero.eclihand.controller.EclihandController;
import com.pedrero.eclihand.controller.WindowController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.window.GenericSearchModalWindow;
import com.pedrero.eclihand.utils.ui.UICallback;
import com.pedrero.eclihand.utils.ui.worker.AsynchronousUIWorker;
import com.pedrero.eclihand.utils.ui.worker.UIAction;
import com.vaadin.ui.Window;

public class GenericSearchModalWindowController<T extends DataObjectDto>
		implements EclihandController, WindowController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5117454372676672562L;


	private GenericSearchModalWindow<T> genericSearchModalWindow;

	private DataObjectService<T> service;

	private UICallback<T> callback;
	
	private WindowController superWindowController;

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
		getGenericSearchModalWindow().getProgressIndicator().setEnabled(true);
		UIAction action = new SearchAndDisplayUIAction(criterium,
				RequestContextHolder.getRequestAttributes());
		AsynchronousUIWorker worker = new AsynchronousUIWorker();
		worker.setAction(action);
		worker.start();
	}

	public void validateChoice(Collection<T> selection){
		for (T selected : selection) {
			getCallback().execute(selected);
		}
	}
	
	private class SearchAndDisplayUIAction implements UIAction{
		
		public SearchAndDisplayUIAction(String criterium,
				RequestAttributes attributes) {
			super();
			this.criterium = criterium;
			this.attributes = attributes;
		}

		private String criterium;

		private RequestAttributes attributes;

		@Override
		public void run() {
			RequestContextHolder.setRequestAttributes(attributes);
			List<T> results = getService().searchByCriterium(criterium);
			synchronized (getGenericSearchModalWindow().getApplication()) {
				getGenericSearchModalWindow().feedTableWith(results);
				getGenericSearchModalWindow().getProgressIndicator()
						.setEnabled(false);
			}
			RequestContextHolder.resetRequestAttributes();
			
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
