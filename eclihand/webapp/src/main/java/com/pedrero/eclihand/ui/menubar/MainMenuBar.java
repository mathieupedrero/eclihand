package com.pedrero.eclihand.ui.menubar;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.menubar.MainMenuBarController;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.vaadin.ui.MenuBar;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MainMenuBar extends MenuBar implements Initiable {

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private MainMenuBarController mainMenuBarController;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3496562499150759806L;

	private MenuItem homeMenuItem;

	private MenuItem teamsMenuItem;

	@Override
	public void init() {
		homeMenuItem = new MenuItem(messageResolver.getMessage("common.home"),
				null,
				new Command() {
					private static final long serialVersionUID = 221385883825946509L;

					@Override
					public void menuSelected(MenuItem selectedItem) {
						mainMenuBarController.goToHome();
					}
				});
		teamsMenuItem = new MenuItem(
				messageResolver.getMessage("common.teams"), null,
				new Command() {
					private static final long serialVersionUID = 8061140994075473288L;

					@Override
					public void menuSelected(MenuItem selectedItem) {
						mainMenuBarController.goToTeams();
					}
				});
		this.getItems().add(homeMenuItem);
		this.getItems().add(teamsMenuItem);
		this.requestRepaint();
	}

}