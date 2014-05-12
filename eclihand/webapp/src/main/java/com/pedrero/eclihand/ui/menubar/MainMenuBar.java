package com.pedrero.eclihand.ui.menubar;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.ui.IFrameElement;
import com.pedrero.eclihand.ui.menubar.menuitem.EclihandMenuItemBuilder;
import com.pedrero.eclihand.ui.menubar.menuitem.EclihandMenuItemModel;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.vaadin.ui.MenuBar;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MainMenuBar extends MenuBar implements IFrameElement {

	public MainMenuBar() {
		super();
	}

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandMenuItemBuilder menuItemBuilder;

	private List<EclihandMenuItemModel> menuItemModels;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3496562499150759806L;

	@PostConstruct
	public void postConstruct() throws Exception {
		// homeMenuItem = new
		// MenuItem(messageResolver.getMessage("common.home"),
		// null, new Command() {
		// private static final long serialVersionUID = 221385883825946509L;
		//
		// @Override
		// public void menuSelected(MenuItem selectedItem) {
		// mainMenuBarController.goToHome();
		// }
		// });
		// teamsMenuItem = new MenuItem(
		// messageResolver.getMessage("common.teams"), null,
		// new Command() {
		// private static final long serialVersionUID = 8061140994075473288L;
		//
		// @Override
		// public void menuSelected(MenuItem selectedItem) {
		// mainMenuBarController.goToTeams();
		// }
		// });
		// playersMenuItem = new MenuItem(
		// messageResolver.getMessage("common.players"), null,
		// new Command() {
		//
		// /**
		// *
		// */
		// private static final long serialVersionUID = -6952213770665073413L;
		//
		// @Override
		// public void menuSelected(MenuItem selectedItem) {
		// mainMenuBarController.goToPlayers();
		// }
		// });

		initMenuBar();
	}

	@Override
	public void refresh() {
		initMenuBar();
	}

	private void initMenuBar() {
		this.getItems().clear();
		for (EclihandMenuItemModel model : menuItemModels) {
			this.getItems().add(menuItemBuilder.buildMenuItemFor(this, model));
		}
	}

}
