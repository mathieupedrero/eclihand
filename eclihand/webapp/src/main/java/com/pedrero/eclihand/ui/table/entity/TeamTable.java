package com.pedrero.eclihand.ui.table.entity;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamPanelController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.ui.table.config.TableConfig;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamTable extends GenericTable<TeamDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6887751870810181541L;

	@Resource(name = "teamTableConfig")
	private TableConfig tableConfig;

	@Resource
	private TeamPanelController entityDisplayerController;

	@Override
	public TableConfig getTableConfig() {
		return tableConfig;
	}

	public void setTableConfig(TableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}

	@Override
	public TeamPanelController getEntityDisplayerController() {
		return entityDisplayerController;
	}

	public void setEntityDisplayerController(
			TeamPanelController entityDisplayerController) {
		this.entityDisplayerController = entityDisplayerController;
	}

}
