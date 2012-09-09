package com.pedrero.eclihand.ui.table.entity;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.PlayerPanelController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.ui.table.config.TableConfig;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlayerTable extends GenericTable<PlayerDto> {

	@Resource(name = "playerTableConfig")
	private TableConfig tableConfig;

	@Resource
	private PlayerPanelController entityDisplayerController;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TableConfig getTableConfig() {
		return tableConfig;
	}

	public void setTableConfig(TableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}

	@Override
	public PlayerPanelController getEntityDisplayerController() {
		return entityDisplayerController;
	}

	public void setEntityDisplayerController(
			PlayerPanelController entityDisplayerController) {
		this.entityDisplayerController = entityDisplayerController;
	}

}
