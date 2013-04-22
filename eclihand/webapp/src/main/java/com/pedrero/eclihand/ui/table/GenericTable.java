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
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityPanel;
import com.pedrero.eclihand.ui.table.config.TableColumnConfig;
import com.pedrero.eclihand.ui.table.config.TableConfig;
import com.pedrero.eclihand.utils.DisplayedEntity;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.UpdatableContentController;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.UpdatableContentManager;
import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;

public class GenericTable<T extends DataObjectDto> extends AbstractEntityPanel
		implements UpdatableContentDisplayer, Initiable {

	private Table dataTable;

	private IndexedContainer container;

	private final Map<Long, DisplayedEntity<T>> dataObjects = new HashMap<Long, DisplayedEntity<T>>();

	private Collection<T> initialDataObjectsList;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private BodyPanelController bodyPanelController;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private LocaleContainer localeContainer;

	private EntityDisplayerController<T> entityDisplayerController;

	private TableConfig tableConfig;

	private GenericTableController<T> genericTableController;

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

	/**
	 * Creates a freshly new {@link GenericTable}
	 */
	public GenericTable() {
		super();
	}

	@Override
	public void init() {
		setShowButtons(tableConfig.getShowsEditButtons());
		setShowDeleteButton(false);

		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		layout.removeAllComponents();

		initializeUIComponents();
		dataTableInit();
		layout.addComponent(dataTable);

		super.init();

		getButtonsLayout().addComponent(removeAll);
		getButtonsLayout().addComponent(add);

	}

	/**
	 * Refreshes visibility and accessibility for the {@link GenericTable}
	 */
	public void refreshButtonsState() {
		removeAll.setVisible(getUpdatable());
		removeAll.setEnabled(getUpdatable());
		add.setVisible(getUpdatable());
		add.setEnabled(getUpdatable());
	}

	private void initializeUIComponents() {
		dataTable = new Table();

		removeAll = eclihandUiFactory.createButton();
		removeAll.setCaption("remove.all");
		removeAll.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (getUpdatable()) {
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
				if (getUpdatable()) {
					getGenericTableController()
							.getGenericSearchModalWindowController()
							.openWindow();
				}
			}
		});
	}

	/**
	 * Initializes the {@link GenericTable} (creates columns...).
	 */
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
		if (getUpdatable()) {
			container.addContainerProperty(UpdatableContentManager.class,
					Button.class, null);

			dataTable.setColumnHeader(UpdatableContentManager.class, "remove");
		}

		dataTable.setContainerDataSource(container);
		dataTable.setCaption(messageResolver.getMessage(getTableConfig()
				.getCaptionKey()));
	}

	/**
	 * Adds {@link DataObjectDto} to the {@link GenericTable}.
	 * 
	 * @param object
	 *            the {@link DataObjectDto} to add
	 */
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
				displayedValue = columnConfig.getFormatter()
						.convertToPresentation(value,
								localeContainer.getLocale());
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
						bodyPanelController
								.showComponent(getEntityDisplayerController()
										.getEntityDisplayerComponent());
						getEntityDisplayerController().display(entity);
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

		if (getUpdatable()) {
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
			item.getItemProperty(UpdatableContentManager.class).setValue(
					deleteButton);
		}
	}

	/**
	 * Adds many {@link DataObjectDto} to this {@link GenericTable}.
	 * 
	 * @param objects
	 *            The {@link DataObjectDto} to add to the {@link GenericTable}
	 */
	public void add(Iterable<T> objects) {
		if (objects != null) {
			for (T object : objects) {
				add(object);
			}
		}
	}

	/**
	 * Initializes data of the {@link GenericTable} feedng it with the submitted
	 * objects.
	 * 
	 * @param objects
	 *            the {@link DataObjectDto} to initialize with the
	 *            {@link GenericTable}
	 */
	public void feed(Collection<T> objects) {
		initialDataObjectsList = objects;
		add(objects);
	}

	/**
	 * Removes an element from the {@link GenericTable}.
	 * 
	 * @param object
	 *            the {@link DataObjectDto} to remove
	 */
	public void remove(T object) {
		container.removeItem(object.getId());
		dataObjects.remove(object.getId());
	}

	/**
	 * Removes many {@link DataObjectDto} from the {@link GenericTable}.
	 * 
	 * @param objects
	 *            the {@link DataObjectDto}s to remove
	 */
	public void remove(Iterable<T> objects) {
		for (T object : objects) {
			remove(object);
		}
	}

	/**
	 * Clears the {@link GenericTable} from its contained objects.
	 */
	public void removeAllDataObjects() {
		container.removeAllItems();
		dataObjects.clear();
	}

	/**
	 * Refreshes the {@link GenericTable} with the initial {@link DataObjectDto}
	 * list.
	 */
	public void refreshData() {
		this.removeAllDataObjects();
		this.add(initialDataObjectsList);
	}

	/**
	 * Commits the modifications made to the {@link GenericTable} data.
	 */
	public void saveData() {
		List<T> entityDisplayed = new ArrayList<T>();

		for (DisplayedEntity<T> displayedEntity : dataObjects.values()) {
			T entity = displayedEntity.getEntity();
			entityDisplayed.add(entity);
		}

		initialDataObjectsList = entityDisplayed;
	}

	/**
	 * Retrieves the {@link GenericTable} selection
	 * 
	 * @return
	 */
	public Collection<T> retrieveSelection() {
		List<T> selection = new ArrayList<T>();
		for (Object itemId : dataTable.getItemIds()) {
			if (dataTable.isSelected(itemId)) {
				selection.add(dataObjects.get(itemId).getEntity());
			}
		}
		return selection;
	}

	/**
	 * @return the {@link EntityDisplayerController} associated to this
	 *         {@link GenericTable}
	 */
	public EntityDisplayerController<T> getEntityDisplayerController() {
		return entityDisplayerController;
	}

	/**
	 * Sets the {@link EntityDisplayerController} associated to this
	 * {@link GenericTable}
	 * 
	 * @param entityDisplayerController
	 *            the {@link EntityDisplayerController} to associate with this
	 *            {@link GenericTable}
	 */
	public void setEntityDisplayerController(
			EntityDisplayerController<T> entityDisplayerController) {
		this.entityDisplayerController = entityDisplayerController;
	}

	/**
	 * Gets the {@link TableConfig}
	 * 
	 * @return the {@link TableConfig}
	 */
	public TableConfig getTableConfig() {
		return tableConfig;
	}

	/**
	 * Sets the {@link TableConfig}
	 * 
	 * @param tableConfig
	 *            the {@link TableConfig} to set.
	 */
	public void setTableConfig(TableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}

	/**
	 * Sets the controller of the {@link GenericTable}
	 * 
	 * @return the controller of the {@link GenericTable}
	 */
	public GenericTableController<T> getGenericTableController() {
		return genericTableController;
	}

	/**
	 * @param Sets
	 *            the controller of the Table.
	 */
	public void setGenericTableController(
			GenericTableController<T> genericTableController) {
		this.genericTableController = genericTableController;
	}

	public void validateChanges() {
		getGenericTableController().validateChanges();
	}

	/**
	 * retrieves the data contained in the {@link GenericTable}
	 * 
	 * @return the data contained in the {@link GenericTable}.
	 */
	public Collection<T> retrieveData() {
		return initialDataObjectsList;
	}

	@Override
	public void makeUpdatable() {
		boolean updateMode = true;
		setTableUpdatable(updateMode);
	}

	@Override
	public void makeCreateMode() {
		boolean updateMode = true;
		setTableUpdatable(updateMode);
	}

	@Override
	public void makeReadOnly() {
		boolean updateMode = false;
		setTableUpdatable(updateMode);
	}

	private void setTableUpdatable(boolean updateMode) {
		this.setUpdatable(updateMode);
		this.refreshButtonsState();
		this.dataTableInit();
		this.refreshData();
	}

	@Override
	public List<UpdatableContentDisplayer> getContentDisplayers() {
		return null;
	}

	@Override
	public UpdatableContentController getController() {
		return genericTableController;
	}

	@Override
	public Layout getMainLayout() {
		return layout;
	}
}
