package com.pedrero.eclihand.ui.custom;

import javax.annotation.Resource;

import org.mvel2.MVEL;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.custom.config.PropertyDisplayerConfig;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public abstract class GenericPropertyDisplayer<T extends DataObjectDto> extends
 Panel
 implements Initiable, EntityDisplayerComponent<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698759988513402341L;

	private T displayed;

	private GridLayout layout;

	@Resource
	private MessageResolver messageResolver;

	@Override
	public void init() {
		Integer rowNumber = getConfig().getProperties().size();
		layout = new GridLayout(2, rowNumber);
		this.setContent(layout);

		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Label label = new Label(messageResolver.getMessage(property
					.getLabelKey()));
			layout.addComponent(label, 0, currentRow);
			layout.setComponentAlignment(label, Alignment.MIDDLE_RIGHT);
			Label value = new Label();
			layout.addComponent(value, 1, currentRow);
			layout.setComponentAlignment(value, Alignment.MIDDLE_LEFT);
			currentRow++;
		}
		
	}

	@Override
	public void display(T entity) {
		displayed = entity;
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Label value = (Label) layout.getComponent(1, currentRow);
			Object rawValue = MVEL.eval(property.getValuePath(), displayed);
			Object displayedValue;
			if (property.getFormatter() != null) {
				displayedValue = property.getFormatter().format(rawValue);
			} else {
				displayedValue = rawValue;
			}

			value.setValue(displayedValue);
			currentRow++;
		}
	}

	public abstract PropertyDisplayerConfig getConfig();

}
