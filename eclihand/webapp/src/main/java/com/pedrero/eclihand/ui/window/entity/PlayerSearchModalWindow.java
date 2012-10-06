package com.pedrero.eclihand.ui.window.entity;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.controller.window.entity.PlayerSearchModalWindowController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.ui.table.entity.PlayerTableSelectOne;
import com.pedrero.eclihand.ui.window.GenericSearchModalWindow;
import com.pedrero.eclihand.utils.text.MessageResolver;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlayerSearchModalWindow extends
		GenericSearchModalWindow<PlayerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8727224130601368895L;

	@Resource
	private PlayerTableSelectOne playerTableSelectOne;

	@Resource
	private PlayerSearchModalWindowController playerSearchModalWindowController;

	@Resource
	private MessageResolver messageResolver;

	@Override
	public void init() {
		super.init();
		this.getTitleLabel().setCaption(
				messageResolver.getMessage("search.players"));
		this.getCancelButton().setCaption(
				messageResolver.getMessage("common.cancel"));
		this.getValidateButton().setCaption(
				messageResolver.getMessage("common.lets.go"));
		this.getSearchButton().setCaption(
				messageResolver.getMessage("common.search"));
	}

	@Override
	public GenericTable<PlayerDto> getDisplayGenericTable() {
		return playerTableSelectOne;
	}

	@Override
	public GenericSearchModalWindowController<PlayerDto> getController() {
		return playerSearchModalWindowController;
	}

}
