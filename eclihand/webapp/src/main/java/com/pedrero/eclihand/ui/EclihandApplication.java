package com.pedrero.eclihand.ui;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.window.EclihandMainWindowController;
import com.pedrero.eclihand.ui.window.EclihandMainWindow;
import com.vaadin.Application;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EclihandApplication extends Application {

	@Resource
	private EclihandMainWindow eclihandMainWindow;

	@Resource
	private EclihandMainWindowController eclihandMainWindowController;

	@Resource
	private MessageSource messageSource;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5162173188485477058L;

	@Override
	public void init() {
		setMainWindow(eclihandMainWindow);
		eclihandMainWindowController.init();
	}

	public EclihandMainWindow getEclihandMainWindow() {
		return eclihandMainWindow;
	}

	public void setEclihandMainWindow(EclihandMainWindow eclihandMainWindow) {
		this.eclihandMainWindow = eclihandMainWindow;
	}

}
