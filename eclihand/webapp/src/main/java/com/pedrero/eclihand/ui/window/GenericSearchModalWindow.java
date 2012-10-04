package com.pedrero.eclihand.ui.window;

import javax.annotation.Resource;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public abstract class GenericSearchModalWindow<T extends DataObjectDto> extends
		Window
		implements Initiable {
	
	@Resource
	private EclihandUiFactory eclihandUiFactory;
	
	private Label titleLabel;

	private TextField serarchTextField;

	private Button searchButton;

	private Button validateButton;

	private Button cancelButton;

	@Override
	public void init() {
		this.setModal(true);
	}

	public abstract DataObjectService<T> getDataObjectService();

	public abstract GenericTable<T> getDisplayGenericTable();

	public void feedTableWith(Iterable<T> objects) {
		getDisplayGenericTable().removeAllItems();
		getDisplayGenericTable().add(objects);
	}

}
