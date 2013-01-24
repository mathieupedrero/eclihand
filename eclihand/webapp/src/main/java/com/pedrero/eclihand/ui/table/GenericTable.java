package com.pedrero.eclihand.ui.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mvel2.MVEL;

import com.pedrero.eclihand.controller.EntityDisplayerController;
import com.pedrero.eclihand.controller.panel.BodyPanelController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.table.config.TableColumnConfig;
import com.pedrero.eclihand.ui.table.config.TableConfig;
import com.pedrero.eclihand.utils.DisplayedEntity;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;

public abstract class GenericTable<T extends DataObjectDto> extends
		Table {

	private IndexedContainer container;
	
	private final Map<Long, DisplayedEntity<T>> dataObjects = new HashMap<Long, DisplayedEntity<T>>();

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private BodyPanelController bodyPanelController;
	
	@Resource
	private EclihandUiFactory eclihandUiFactory;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5410222330558478558L;

	public GenericTable() {
		super();
	}

	public void init() {
		setSelectable(getTableConfig().getCanSelect());
		setMultiSelect(getTableConfig().getCanMultiSelect());
		container = new IndexedContainer();
		for (TableColumnConfig columnConfig : getTableConfig()
				.getColumnConfigs()) {

			if (columnConfig.getIsLink()
					&& getTableConfig().getCanRedirectToEntityDisplayer()) {
				container.addContainerProperty(columnConfig.getId(),
						Button.class, null);

			} else {
				container.addContainerProperty(columnConfig.getId(),
						columnConfig.getEnumType().getType(), null);
			}
			this.setColumnHeader(columnConfig.getId(),
					messageResolver.getMessage(columnConfig.getLabelKey()));
		}
		setContainerDataSource(container);
		setCaption(messageResolver.getMessage(getTableConfig().getCaptionKey()));
	}

	public void add(T object) {
		DisplayedEntity<T> displayedEntity = new DisplayedEntity<T>();
		displayedEntity.setEntity(object);
		dataObjects.put(object.getId(), displayedEntity);

		Item item = container.addItem(object.getId());

		List<Object> descriptionParams = new ArrayList<Object>();

		// List of linkButtons created for the line of the table
		List<Button> linkButtonList = new ArrayList<Button>();

		for (TableColumnConfig columnConfig : getTableConfig()
				.getColumnConfigs()) {
			Object value = MVEL.eval(columnConfig.getValuePath(), object);
			Object displayedValue = value;

			if (columnConfig.getFormatter() != null) {
				displayedValue = columnConfig.getFormatter().format(value);
			}

			// Gathering information for description computing
			if (columnConfig.getIsDescriptionParam()) {
				descriptionParams.add(displayedValue);
			}

			if (columnConfig.getIsLink()
					&& getTableConfig().getCanRedirectToEntityDisplayer()) {
				// If column is a link (to redirect to entity displayer panel,
				// adds a button link as data
				
				Button linkButton = eclihandUiFactory.createLinkButton();
				linkButton.setCaption(displayedValue.toString());
				linkButton.setData(object.getId());
				
				linkButton.addListener(new Button.ClickListener() {

					/**
					* 
					*/
					private static final long serialVersionUID = -9137527610306296567L;

					@Override
					public void buttonClick(ClickEvent event) {
						T entity = dataObjects.get(event.getButton().getData())
								.getEntity();
						getEntityDisplayerController().display(entity);
						bodyPanelController
								.showComponent(getEntityDisplayerController()
										.getEntityDisplayerComponent());

					}
				});

				linkButtonList.add(linkButton);
				linkButton.setDescription(dataObjects.get(object.getId())
						.getDescription());

				item.getItemProperty(columnConfig.getId()).setValue(linkButton);
			} else {
				// else adds raw data
				item.getItemProperty(columnConfig.getId()).setValue(
						displayedValue);
			}
			
		}
		// computing description with gathered information
		displayedEntity.setDescription(messageResolver.getMessage(
				getTableConfig().getLineDescriptionKey(),
				descriptionParams.toArray()));

		// feeding buttons description
		for (Button linkButton : linkButtonList) {
			linkButton.setDescription(displayedEntity.getDescription());
		}
	}
	
	public void add(Iterable<T> objects){
		for (T object : objects){
			add(object);
		}
	}
	
	public void remove(T object){
		container.removeItem(object.getId());
		dataObjects.remove(object.getId());
	}
	
	public void remove(Iterable<T> objects){
		for (T object : objects){
			remove(object);
		}
	}

	public void removeAllDataObjects() {
		container.removeAllItems();
		dataObjects.clear();
	}

	public Collection<T> retrieveSelection() {
		List<T> selection = new ArrayList<T>();
		for (Object itemId : this.getItemIds()) {
			if (isSelected(itemId)) {
				selection.add(dataObjects.get(itemId).getEntity());
			}
		}
		return selection;
	}

	public abstract TableConfig getTableConfig();

	public abstract EntityDisplayerController<T> getEntityDisplayerController();
}
