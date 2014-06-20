package com.pedrero.eclihand.ui.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.ui.panel.entity.EditMode;
import com.pedrero.eclihand.ui.table.config.TableColumnConfig;
import com.pedrero.eclihand.ui.table.config.TableConfig;
import com.pedrero.eclihand.ui.window.GenericSearchModalWindow;
import com.pedrero.eclihand.utils.DisplayedEntity;
import com.pedrero.eclihand.utils.spring.EclihandBeanFactory;
import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.pedrero.eclihand.utils.ui.UICallback;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;

public class GenericTable<T extends DataObjectDto> extends AbstractEntityComponent {

	private static final Object REMOVE_BUTTON_PROPERTY_KEY = new Object();

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericTable.class);

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private LocaleContainer localeContainer;

	@Resource
	private EclihandBeanFactory beanFactory;

	private TableConfig tableConfig;

	private final Table dataTable = new Table();

	private IndexedContainer container;

	private final Map<Long, DisplayedEntity<T>> dataObjects = new HashMap<Long, DisplayedEntity<T>>();

	private Collection<T> initialDataObjectsList;

	private Layout layout;

	private Class<? extends GenericSearchModalWindow<T>> searchModalWindowClass;

	private DataObjectService<T> service;

	public GenericTable(EditMode editMode) {
		super(editMode);
	}

	private void initializeButtons() {
		LOGGER.debug("Initializing data table UI components");

		Button removeAll = eclihandUiFactory.createButton();
		removeAll.setCaption("remove.all");
		removeAll.addClickListener(new Button.ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!getEditMode().isReadOnly()) {
					removeAllDataObjectsFromTable();
					initialDataObjectsList.clear();
				}
			}
		});
		removeAll.setVisible(true);
		removeAll.setEnabled(true);

		Button add = eclihandUiFactory.createButton();
		add.setCaption("add");
		add.addClickListener(new Button.ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!getEditMode().isReadOnly()) {
					getComponent().getUI().addWindow(beanFactory.getBean(searchModalWindowClass, new UICallback<T>() {
						@Override
						public void execute(T dataObject) {
							addToTable(dataObject);
							initialDataObjectsList.add(dataObject);
						}
					}, service));
				}
			}
		});
		add.setVisible(true);
		add.setEnabled(true);

		HorizontalLayout buttonsLayout = eclihandLayoutFactory.createCommonHorizontalLayout();
		layout.addComponent(buttonsLayout);
		buttonsLayout.addComponent(add);
		buttonsLayout.addComponent(removeAll);
	}

	/**
	 * Initializes the {@link GenericTable} (creates columns...).
	 */
	public void dataTableInit() {
		LOGGER.debug("initializing Table for [{}]", getTableConfig().getCaptionKey());

		dataTable.setSelectable(getTableConfig().getCanSelect());
		dataTable.setMultiSelect(getTableConfig().getCanMultiSelect());
		container = new IndexedContainer();
		for (TableColumnConfig columnConfig : getTableConfig().getColumnConfigs()) {
			LOGGER.debug("initializing column in table for [{}]", columnConfig.getLabelKey());

			if (columnConfig.getAction() != null && getTableConfig().getActionsEnabled()) {
				container.addContainerProperty(columnConfig.getId(), Button.class, null);

			} else {
				container.addContainerProperty(columnConfig.getId(), columnConfig.getEnumType().getType(), null);
			}
			dataTable.setColumnHeader(columnConfig.getId(), messageResolver.getMessage(columnConfig.getLabelKey()));
		}
		if (!getEditMode().isReadOnly()) {
			container.addContainerProperty(REMOVE_BUTTON_PROPERTY_KEY, Button.class, null);

			dataTable.setColumnHeader(REMOVE_BUTTON_PROPERTY_KEY, "remove");
		}

		dataTable.setContainerDataSource(container);
		dataTable.setCaption(messageResolver.getMessage(getTableConfig().getCaptionKey()));
	}

	/**
	 * Adds {@link DataObjectDto} to the {@link GenericTable}.
	 * 
	 * @param object
	 *            the {@link DataObjectDto} to add
	 */
	@SuppressWarnings("unchecked")
	public void addToTable(T object) {
		DisplayedEntity<T> displayedEntity = new DisplayedEntity<T>();
		displayedEntity.setEntity(object);
		dataObjects.put(object.getId(), displayedEntity);

		LOGGER.debug("creating item table for object with id {}", object.getId());
		Item item = container.addItem(object.getId());

		List<Object> descriptionParams = new ArrayList<Object>();

		// List of linkButtons created for the line of the table
		List<Button> linkButtonList = new ArrayList<Button>();

		for (final TableColumnConfig columnConfig : getTableConfig().getColumnConfigs()) {
			Object value = MVEL.eval(columnConfig.getValuePath(), object);

			if (columnConfig.getFormatter() != null) {
				value = columnConfig.getFormatter().convertToPresentation(value, String.class,
						localeContainer.getLocale());
			}

			// Gathering information for description computing
			if (columnConfig.getIsDescriptionParam()) {
				descriptionParams.add(value);
			}

			if (columnConfig.getAction() != null && getTableConfig().getActionsEnabled()) {
				// If column is a link (to redirect to entity displayer panel,
				// adds a button link as data

				Button linkButton = eclihandUiFactory.createLinkButton();
				linkButton.setCaption(value.toString());
				linkButton.setData(object.getId());

				linkButton.addClickListener(new Button.ClickListener() {

					/**
					* 
					*/
					private static final long serialVersionUID = -9137527610306296567L;

					@Override
					public void buttonClick(ClickEvent event) {
						T entity = dataObjects.get(event.getButton().getData()).getEntity();
						columnConfig.getAction().performAction(entity.getId());
					}
				});

				linkButtonList.add(linkButton);
				linkButton.setDescription(dataObjects.get(object.getId()).getDescription());

				LOGGER.debug("item [{}]", item);
				LOGGER.debug("columnConfig {}", columnConfig);
				LOGGER.debug("columnConfig.id {}", columnConfig.getId());
				item.getItemProperty(columnConfig.getId()).setValue(linkButton);
			} else {
				// else adds raw data
				item.getItemProperty(columnConfig.getId()).setValue(value);
			}

		}
		// computing description with gathered information
		displayedEntity.setDescription(messageResolver.getMessage(getTableConfig().getLineDescriptionKey(),
				descriptionParams.toArray()));

		// feeding buttons description
		for (Button linkButton : linkButtonList) {
			linkButton.setDescription(displayedEntity.getDescription());
		}

		if (!getEditMode().isReadOnly()) {
			Button deleteButton = eclihandUiFactory.createLinkButton();
			deleteButton.setData(object);

			deleteButton.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = -6563780592033942016L;

				@Override
				public void buttonClick(ClickEvent event) {
					removeFromTable((T) event.getButton().getData());
				}
			});
			deleteButton.setCaption("delete");
			item.getItemProperty(REMOVE_BUTTON_PROPERTY_KEY).setValue(deleteButton);
		}
	}

	/**
	 * Adds many {@link DataObjectDto} to this {@link GenericTable}.
	 * 
	 * @param objects
	 *            The {@link DataObjectDto} to add to the {@link GenericTable}
	 */
	public void addToTable(Iterable<T> objects) {
		if (objects != null) {
			for (T object : objects) {
				LOGGER.debug("adding object with id {} to table.", object.getId());
				addToTable(object);
			}
		}
	}

	/**
	 * Initializes data of the {@link GenericTable} feeding it with the
	 * submitted objects.
	 * 
	 * @param objects
	 *            the {@link DataObjectDto} to initialize with the
	 *            {@link GenericTable}
	 */
	public void feed(Collection<T> objects) {
		LOGGER.debug("feeding table with object list ({} elements)", objects.size());
		initialDataObjectsList = objects;
		addToTable(objects);
	}

	/**
	 * Removes an element from the {@link GenericTable}.
	 * 
	 * @param object
	 *            the {@link DataObjectDto} to remove
	 */
	public void removeFromTable(T object) {
		container.removeItem(object.getId());
		dataObjects.remove(object.getId());
	}

	/**
	 * Removes many {@link DataObjectDto} from the {@link GenericTable}.
	 * 
	 * @param objects
	 *            the {@link DataObjectDto}s to remove
	 */
	public void removeFromTable(Iterable<T> objects) {
		for (T object : objects) {
			removeFromTable(object);
		}
	}

	/**
	 * Clears the {@link GenericTable} from its contained objects.
	 */
	public void removeAllDataObjectsFromTable() {
		container.removeAllItems();
		dataObjects.clear();
	}

	/**
	 * Refreshes the {@link GenericTable} with the initial {@link DataObjectDto}
	 * list.
	 */
	public void refreshData() {
		LOGGER.debug("Data table refresh");
		this.addToTable(initialDataObjectsList);
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

	public TableConfig getTableConfig() {
		return tableConfig;
	}

	public void setTableConfig(TableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}

	public Collection<T> retrieveData() {
		return initialDataObjectsList;
	}

	@Override
	@PostConstruct
	protected void postConstruct() {
		LOGGER.debug("GenericTable bean initialization");
		super.postConstruct();

		dataTableInit();
		if (!getEditMode().isReadOnly()) {
			initializeButtons();
		}
		this.refreshData();

		this.dataTable.setPageLength(5);
	}
}
