package com.pedrero.eclihand.ui.custom;

import javax.annotation.Resource;

import org.mvel2.MVEL;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.custom.config.PropertyDisplayerConfig;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.ui.panel.entity.EditMode;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.GridLayout;

/**
 * @author mpedrero
 * 
 *         This component can display properties of any Data Object
 * 
 * @param <T>
 *            {@link DataObjectDto} type to be displayed
 */
public abstract class AbstractGenericPropertyDisplayer<T extends DataObjectDto> extends AbstractEntityComponent {

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	/**
	 * The configuration of this Property Displayer
	 */
	private PropertyDisplayerConfig config;

	/**
	 * The Data Object displayed by this entity displayer
	 */
	private final T displayed;

	public AbstractGenericPropertyDisplayer(EditMode editMode, T displayed) {
		super(editMode);
		this.displayed = displayed;
	}

	/**
	 * The display of the property displayer (as a grid)
	 */
	private GridLayout layout;

	@Override
	protected void postConstruct() {
		Integer rowNumber = getConfig().getProperties().size();
		layout = eclihandLayoutFactory.createCommonGridLayout(2, rowNumber + 1);
		super.postConstruct();
		getMainLayout().addComponent(layout);
		display(displayed);
	}

	private void display(T entity) {
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Object rawValue = MVEL.eval(property.getValuePath(), displayed);
			createAndAddComponent(currentRow, property, rawValue);
			currentRow++;
		}
	}

	abstract protected void createAndAddComponent(Integer currentRow, PropertyConfig property, Object rawValue);

	public PropertyDisplayerConfig getConfig() {
		return config;
	}

	public void setConfig(PropertyDisplayerConfig config) {
		this.config = config;
	}

	@Override
	public GridLayout getMainLayout() {
		return layout;
	}

	public T getDisplayed() {
		return displayed;
	}

}
