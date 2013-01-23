package com.pedrero.eclihand.ui.custom;

import java.util.Date;

import javax.annotation.Resource;

import org.mvel2.MVEL;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.EntityDisplayerComponent;
import com.pedrero.eclihand.ui.custom.config.PropertyConfig;
import com.pedrero.eclihand.ui.custom.config.PropertyDisplayerConfig;
import com.pedrero.eclihand.utils.EclihandUiException;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.Formatter;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

/**
 * @author mpedrero
 *
 *	This component can display properties of any Data Object
 *
 * @param <T>
 */
public class GenericPropertyDisplayer<T extends DataObjectDto> extends
 Panel
 implements Initiable, EntityDisplayerComponent<T> {

	private static final String VALIDATE_CHANGES_KEY = "validate.changes";

	private static final String DISCARD_CHANGES_KEY = "discard.changes";

	private static final String MAKE_UPDATABLE_KEY = "make.updatable";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698759988513402341L;
	
	/**
	 * The configuration of this Property Displayer
	 */
	private PropertyDisplayerConfig config;

	/**
	 * The Data Object displayed by this entity displayer
	 */
	private T displayed;

	/**
	 * The display of the property displayer (as a grid)
	 */
	private GridLayout layout;
	
	/**
	 * Tells wether the data displayed can be updated or not
	 */
	private Boolean updatable = false;

	/**
	 * Button to switch to update mode
	 */
	private Button switchUpdateModeButton;

	/**
	 * Button to validate changes made in update mode
	 */
	private Button validateChanges;

	@Resource
	private MessageResolver messageResolver;
	
	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Override
	public void init() {
		switchUpdateModeButton = eclihandUiFactory.createButton();
		switchUpdateModeButton.setCaption(MAKE_UPDATABLE_KEY);
		validateChanges = eclihandUiFactory.createButton();
		validateChanges.setCaption(VALIDATE_CHANGES_KEY);
		Integer rowNumber = getConfig().getProperties().size();
		layout = new GridLayout(2, rowNumber +1);
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
		layout.addComponent(switchUpdateModeButton, 0, currentRow);
		switchUpdateModeButton.addListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable){
					makeReadOnly();
				}else{
					makeUpdatable();
				}
			}
		});
		validateChanges.addListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable){
					validateChanges();
					makeReadOnly();
				}
			}
		});
		layout.addComponent(validateChanges, 1, currentRow);
		
	}

	@Override
	public void display(T entity) {
		displayed = entity;
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Property value = (Property) layout.getComponent(1, currentRow);
			Object rawValue = MVEL.eval(property.getValuePath(), displayed);
			Object displayedValue;
			if (value instanceof Label && property.getFormatter() != null) {
				displayedValue = property.getFormatter().format(rawValue);
			} else {
				displayedValue = rawValue;
			}

			value.setValue(displayedValue);
			currentRow++;
		}
		//layout.requestRepaint();
	}
	
	public void makeUpdatable(){

		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Component field;
			field = createAndConfigurePropertyComponent(property);
			if (layout.getComponent(1, currentRow) != null){
				layout.removeComponent(1, currentRow);
			}
			layout.addComponent(field, 1, currentRow);
			layout.setComponentAlignment(field, Alignment.MIDDLE_LEFT);
			currentRow++;
		}
		//validateChanges.setVisible(true);
		display(displayed);
		updatable = true;
		switchUpdateModeButton.setCaption(DISCARD_CHANGES_KEY);
	}
	
	public void makeReadOnly(){

		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Label value = new Label();
			if (layout.getComponent(1, currentRow) != null){
				layout.removeComponent(1, currentRow);
			}
			layout.addComponent(value, 1, currentRow);
			layout.setComponentAlignment(value, Alignment.MIDDLE_LEFT);
			currentRow++;
		}
		//validateChanges.setVisible(false);
		display(displayed);
		updatable = false;
		switchUpdateModeButton.setCaption(MAKE_UPDATABLE_KEY);
		
	}
	
	private Component createAndConfigurePropertyComponent(PropertyConfig propertyConfig){
		Class<? extends Object> dataTypeClass = propertyConfig.retrieveClassDataType();
		if (dataTypeClass.equals(String.class) || Number.class.isAssignableFrom(dataTypeClass) || dataTypeClass.isEnum() || dataTypeClass.equals(Date.class)){

			if (propertyConfig.getMaxValue() != null && propertyConfig.getMinValue() != null && propertyConfig.getFormatter() != null){
				Formatter formatter = propertyConfig.getFormatter();
				ComboBox combo = new ComboBox();
				if (Integer.class.equals(dataTypeClass)){
					Integer beginning = Integer.valueOf(propertyConfig.getMinValue());
					Integer end = Integer.valueOf(propertyConfig.getMaxValue());
					for (Integer i = beginning; i<= end; i++){
						combo.addItem(i);
						combo.setItemCaption(i, formatter.format(i));
					}
				}else if (Long.class.equals(dataTypeClass)){
					Long beginning = Long.valueOf(propertyConfig.getMinValue());
					Long end = Long.valueOf(propertyConfig.getMaxValue());
					for (Long i = beginning; i<= end; i++){
						combo.addItem(i);
						combo.setItemCaption(i, formatter.format(i));
					}
				}
				return combo;
			}
			if (dataTypeClass.isEnum()){
				ComboBox combo = new ComboBox();
				for (Object value : dataTypeClass.getEnumConstants()){
					combo.addItem(value);
					String caption;
					if (propertyConfig.getFormatter() != null){
						caption = propertyConfig.getFormatter().format(value);
					}else{
						caption = value.toString();
					}
					combo.setItemCaption(value, caption);
				}
				return combo;
			}
			if (dataTypeClass.equals(Date.class)){
				return new DateField();
			}
			return new TextField();
		}
		throw new EclihandUiException("Configuration exception : datatype "+propertyConfig.getDataType()+" not handled in Config");
	}
	
	private void validateChanges(){
		Integer currentRow = 0;
		for (PropertyConfig property : getConfig().getProperties()) {
			Property value = (Property) layout.getComponent(1, currentRow);
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

}
