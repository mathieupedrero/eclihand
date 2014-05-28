package com.pedrero.eclihand.ui.custom;

import javax.annotation.Resource;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.panel.entity.EditMode;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;

public class ViewGenericPropertyDisplayer<T extends DataObjectDto> extends
		AbstractGenericPropertyDisplayer<DataObjectDto> {

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	public ViewGenericPropertyDisplayer(T displayed) {
		super(EditMode.VIEW, displayed);
	}

	@Override
	protected void createAndAddComponent(Integer currentRow,
			PropertyConfig property, Object rawValue) {
		Label label = createLabelAndAddItAsValueToLine(currentRow);
		if (property.getFormatter() != null) {
			label.setConverter(property.getFormatter());
		}
		if (rawValue != null) {
			label.setPropertyDataSource(new ObjectProperty<Object>(rawValue));
		}

	}

	private Label createLabelAndAddItAsValueToLine(Integer currentRow) {
		Label value = eclihandUiFactory.createLabel();
		getMainLayout().addComponent(value, 1, currentRow);
		getMainLayout().setComponentAlignment(value, Alignment.MIDDLE_LEFT);
		return value;
	}

}
