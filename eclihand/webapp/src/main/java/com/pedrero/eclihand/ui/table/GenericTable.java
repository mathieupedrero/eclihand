package com.pedrero.eclihand.ui.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mvel2.MVEL;

import com.pedrero.eclihand.controller.EntityDisplayerController;
import com.pedrero.eclihand.controller.GenericTableController;
import com.pedrero.eclihand.controller.panel.BodyPanelController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.table.config.TableColumnConfig;
import com.pedrero.eclihand.ui.table.config.TableConfig;
import com.pedrero.eclihand.utils.DisplayedEntity;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class GenericTable<T extends DataObjectDto> extends Panel implements
		UpdatableContentDisplayer {

	private Table dataTable;

	private IndexedContainer container;

	private final Map<Long, DisplayedEntity<T>> dataObjects = new HashMap<Long, DisplayedEntity<T>>();

	private Iterable<T> initialDataObjectsList;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private EntityDisplayerController<T> entityDisplayerController;

	private TableConfig tableConfig;

	private GenericTableController<T> genericTableController;

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

	/**
	 * Button to remove all data from dataTable
	 */
	private Button removeAll;

	/**
	 * Button to add data to dataTable
	 */
	private Button add;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5410222330558478558L;

	public GenericTable() {
		super();
	}

	public void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);

		layout.removeAllComponents();

		updatable = tableConfig.getIsEditModeDefault();

		initializeUIComponents();

		HorizontalLayout buttonsLayout = initializeButtonsLayout();

		refreshButtonsState();

		dataTableInit();

		layout.addComponent(dataTable);
		layout.addComponent(buttonsLayout);
	}

	public void refreshButtonsState() {
		removeAll.setVisible(updatable);
		removeAll.setEnabled(updatable);
		add.setVisible(updatable);
		add.setEnabled(updatable);
		validateChanges.setVisible(updatable);
		validateChanges.setEnabled(updatable);
	}

	private HorizontalLayout initializeButtonsLayout() {
		HorizontalLayout buttonsLayout = eclihandLayoutFactory
				.createCommonHorizontalLayout();

		if (tableConfig.getShowsEditButtons()) {
			buttonsLayout.addComponent(switchUpdateModeButton);
			buttonsLayout.addComponent(validateChanges);
		}

		buttonsLayout.addComponent(removeAll);
		buttonsLayout.addComponent(add);
		return buttonsLayout;
	}

	private void initializeUIComponents() {
		dataTable = new Table();

		switchUpdateModeButton = eclihandUiFactory.createButton();
		getGenericTableController();
		switchUpdateModeButton
				.setCaption(UpdatableContentDisplayer.MAKE_UPDATABLE_KEY);
		switchUpdateModeButton.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					makeReadOnly();
				} else {
					makeUpdatable();
				}
			}
		});

		validateChanges = eclihandUiFactory.createButton();
		getGenericTableController();
		validateChanges
				.setCaption(UpdatableContentDisplayer.VALIDATE_CHANGES_KEY);
		validateChanges.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					validateChanges();
					makeReadOnly();
				}
			}
		});

		removeAll = eclihandUiFactory.createButton();
		removeAll.setCaption("remove.all");
		removeAll.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					removeAllDataObjects();
				}
			}
		});

		add = eclihandUiFactory.createButton();
		add.setCaption("add");
		add.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					getGenericTableController()
							.getGenericSearchModalWindowController()
							.openWindow();
				}
			}
		});
	}

	public void dataTableInit() {
		dataTable.setSelectable(getTableConfig().getCanSelect());
		dataTable.setMultiSelect(getTableConfig().getCanMultiSelect());
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
			dataTable.setColumnHeader(columnConfig.getId(),
					messageResolver.getMessage(columnConfig.getLabelKey()));
		}
		if (updatable) {
			container.addContainerProperty(UpdatableContentDisplayer.class,
					Button.class, null);

			dataTable
					.setColumnHeader(UpdatableContentDisplayer.class, "remove");
		}

		dataTable.setContainerDataSource(container);
		dataTable.setCaption(messageResolver.getMessage(getTableConfig()
				.getCaptionKey()));
	}

	@SuppressWarnings("unchecked")
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

				linkButton.addClickListener(new Button.ClickListener() {

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

		if (updatable) {
			Button deleteButton = eclihandUiFactory.createLinkButton();
			deleteButton.setData(object);

			deleteButton.addClickListener(new Button.ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -6563780592033942016L;

				@Override
				public void buttonClick(ClickEvent event) {
					remove((T) event.getButton().getData());
				}
			});
			deleteButton.setCaption("delete");
			item.getItemProperty(UpdatableContentDisplayer.class).setValue(
					deleteButton);
		}
	}

	public void add(Iterable<T> objects) {
		for (T object : objects) {
			add(object);
		}
	}

	public void feed(Iterable<T> objects) {
		initialDataObjectsList = objects;
		add(objects);
	}

	public void remove(T object) {
		container.removeItem(object.getId());
		dataObjects.remove(object.getId());
	}

	public void remove(Iterable<T> objects) {
		for (T object : objects) {
			remove(object);
		}
	}

	public void removeAllDataObjects() {
		container.removeAllItems();
		dataObjects.clear();
	}

	public void refreshData() {
		this.removeAllDataObjects();
		this.add(initialDataObjectsList);
	}

	public void saveData() {
		List<T> entityDisplayed = new ArrayList<T>();

		for (DisplayedEntity<T> displayedEntity : dataObjects.values()) {
			T entity = displayedEntity.getEntity();
			entityDisplayed.add(entity);
		}

		initialDataObjectsList = entityDisplayed;
	}

	public Collection<T> retrieveSelection() {
		List<T> selection = new ArrayList<T>();
		for (Object itemId : dataTable.getItemIds()) {
			if (dataTable.isSelected(itemId)) {
				selection.add(dataObjects.get(itemId).getEntity());
			}
		}
		return selection;
	}

	public EntityDisplayerController<T> getEntityDisplayerController() {
		return entityDisplayerController;
	}

	public void setEntityDisplayerController(
			EntityDisplayerController<T> entityDisplayerController) {
		this.entityDisplayerController = entityDisplayerController;
	}

	public TableConfig getTableConfig() {
		return tableConfig;
	}

	public void setTableConfig(TableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}

	public Boolean getUpdatable() {
		return updatable;
	}

	public void setUpdatable(Boolean updatable) {
		this.updatable = updatable;
	}

	public GenericTableController<T> getGenericTableController() {
		return genericTableController;
	}

	public void setGenericTableController(
			GenericTableController<T> genericTableController) {
		this.genericTableController = genericTableController;
	}

	@Override
	public void makeUpdatable() {
		getGenericTableController().makeUpdatable();

	}

	@Override
	public void makeReadOnly() {
		getGenericTableController().makeReadOnly();

	}

	@Override
	public void validateChanges() {
		getGenericTableController().validateChanges();
	}
}
