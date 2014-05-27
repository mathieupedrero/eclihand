package com.pedrero.eclihand.ui.custom;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.mvel2.MVEL;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.custom.config.PropertyDisplayerConfig;
import com.pedrero.eclihand.ui.panel.entity.EditMode;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;

/**
 * @author mpedrero
 * 
 *         This component can display properties of any Data Object
 * 
 * @param <T>
 *            {@link DataObjectDto} type to be displayed
 */
public abstract class AbstractGenericPropertyDisplayer<T extends DataObjectDto>
		extends AbstractEntityComponent implements EntityDisplayerComponent<T>,
		UpdatableContentDisplayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698759988513402341L;

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
	private T displayed;

	public AbstractGenericPropertyDisplayer(EditMode editMode) {
		super(editMode);
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
		this.addComponent(layout);

		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			createLabelAndAddItToLineForProperty(currentRow, property);
			currentRow++;
		}
	}

	abstract protected void createAndAddComponent(Integer currentRow,
			PropertyConfig property, Object rawValue);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void display(T entity) {
		displayed = entity;
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Object rawValue = MVEL.eval(property.getValuePath(), displayed);
			createAndAddComponent(currentRow, property, rawValue);
			currentRow++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.ui.custom.UpdatableContentDisplayer#makeUpdatable()
	 */
	@Override
	public void makeUpdatable() {
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			createEditableComponentAndAddItToLineForProperty(currentRow,
					property);
			currentRow++;
		}
		display(displayed);
		super.makeUpdatable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.ui.custom.UpdatableContentDisplayer#makeReadOnly()
	 */
	@Override
	public void makeReadOnly() {
		display(displayed);
		super.makeReadOnly();

	}

	@Override
	public void makeCreateMode() {
		this.makeUpdatable();
	}

	@Override
	public void validateChanges() {
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			@SuppressWarnings("rawtypes")
			Field value = (Field) layout.getComponent(1, currentRow);
			Object rawValue = value.getValue();
			MVEL.setProperty(displayed, property.getValuePath(), rawValue);
			currentRow++;
		}
	}

	public PropertyDisplayerConfig getConfig() {
		return config;
	}

	public void setConfig(PropertyDisplayerConfig config) {
		this.config = config;
	}

	@Override
	public List<UpdatableContentDisplayer> getContentDisplayers() {
		return null;
	}

	@Override
	public GridLayout getMainLayout() {
		return layout;
	}

	@Override
	public Set<Credential> getRequiredCredentials() {
		return null;
	}

	@Override
	public void delete() {
		throw new NotImplementedException();
	}

}
