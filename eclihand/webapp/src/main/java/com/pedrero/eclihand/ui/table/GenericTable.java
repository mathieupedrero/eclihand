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
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

public abstract class GenericTable<T extends DataObjectDto> extends
		Table {

	private IndexedContainer container;
	
	private final Map<Long, DisplayedEntity<T>> dataObjects = new HashMap<Long, DisplayedEntity<T>>();

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private BodyPanelController bodyPanelController;

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
			container.addContainerProperty(columnConfig.getId(), columnConfig
					.getEnumType().getType(), null);
			this.setColumnHeader(columnConfig.getId(),
					messageResolver.getMessage(columnConfig.getLabelKey()));
		}
		setContainerDataSource(container);
		setCaption(messageResolver.getMessage(getTableConfig().getCaptionKey()));

		if (getTableConfig().getCanRedirectToEntityDisplayer()) {
			this.addListener(new ItemClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -9137527610306296567L;

				@Override
				public void itemClick(ItemClickEvent event) {
					T entity = dataObjects.get(event.getItemId()).getEntity();
					getEntityDisplayerController().display(entity);
					bodyPanelController
							.showComponent(getEntityDisplayerController()
									.getEntityDisplayerComponent());
				}
			});
			this.setItemDescriptionGenerator(new ItemDescriptionGenerator() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = -2277015278930299935L;

				@Override
				public String generateDescription(Component source, Object itemId,
						Object propertyId) {
					
					return dataObjects.get(itemId).getDescription();
				}
			});
		}
	}

	public void add(T object) {
		DisplayedEntity<T> displayedEntity = new DisplayedEntity<T>();
		displayedEntity.setEntity(object);
		dataObjects.put(object.getId(), displayedEntity);
		Item item = container.addItem(object.getId());

		List<Object> descriptionParams = new ArrayList<Object>();
		for (TableColumnConfig columnConfig : getTableConfig()
				.getColumnConfigs()) {
			Object value = MVEL.eval(columnConfig.getValuePath(), object);
			Object displayedValue = value;
			if (columnConfig.getFormatter() != null) {
				displayedValue = columnConfig.getFormatter().format(value);
			}
			item.getItemProperty(columnConfig.getId()).setValue(displayedValue
					);
			if (columnConfig.getIsDescriptionParam()) {
				descriptionParams.add(displayedValue);
			}
		}
		displayedEntity.setDescription(messageResolver.getMessage(
				getTableConfig().getLineDescriptionKey(),
				descriptionParams.toArray()));
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
