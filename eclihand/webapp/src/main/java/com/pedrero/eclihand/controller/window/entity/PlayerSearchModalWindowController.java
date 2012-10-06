package com.pedrero.eclihand.controller.window.entity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.controller.WindowController;
import com.pedrero.eclihand.controller.window.EclihandMainWindowController;
import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.service.DataObjectService;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.ui.window.GenericSearchModalWindow;
import com.pedrero.eclihand.ui.window.entity.PlayerSearchModalWindow;
import com.pedrero.eclihand.utils.ui.UICallback;
import com.pedrero.eclihand.utils.ui.impl.PlayerDisplayUICallback;

@Controller
public class PlayerSearchModalWindowController extends
		GenericSearchModalWindowController<PlayerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5539867248715685459L;

	@Resource
	private PlayerSearchModalWindow playerSearchModalWindow;

	@Resource
	private PlayerService playerService;

	@Resource
	private EclihandMainWindowController eclihandMainWindowController;

	@Resource
	private PlayerDisplayUICallback playerDisplayUICallback;

	@Override
	public GenericSearchModalWindow<PlayerDto> getGenericSearchModalWindow() {
		return playerSearchModalWindow;
	}

	@Override
	public DataObjectService<PlayerDto> getService() {
		return playerService;
	}

	@Override
	public UICallback<PlayerDto> getCallback() {
		return playerDisplayUICallback;
	}

	@Override
	public WindowController getSuperWindowController() {
		return eclihandMainWindowController;
	}

}
