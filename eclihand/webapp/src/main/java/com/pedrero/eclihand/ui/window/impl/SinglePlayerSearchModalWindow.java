package com.pedrero.eclihand.ui.window.impl;

import javax.annotation.Resource;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.common.DataObjectService;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.ui.window.GenericSearchModalWindow;
import com.pedrero.eclihand.utils.ui.UICallback;

public class SinglePlayerSearchModalWindow extends
		GenericSearchModalWindow<PlayerDto> {

	@Resource
	private GenericTable<PlayerDto> playerTableSelectOne;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4553312651591255169L;

	public SinglePlayerSearchModalWindow(UICallback<PlayerDto> callback,
			DataObjectService<PlayerDto> service) {
		super(callback, service);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setDisplayGenericTable(playerTableSelectOne);
		super.afterPropertiesSet();
	}

}
