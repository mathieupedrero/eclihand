package com.pedrero.eclihand.ui.menubar;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.security.SecurityController;
import com.pedrero.eclihand.ui.IFrameElement;
import com.pedrero.eclihand.ui.menubar.menuitem.EclihandMenuItemBuilder;
import com.pedrero.eclihand.ui.menubar.menuitem.EclihandMenuItemModel;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.vaadin.ui.MenuBar;

@Component
public class MainMenuBar extends MenuBar implements IFrameElement {

	public MainMenuBar() {
		super();
	}

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandMenuItemBuilder menuItemBuilder;

	@Resource
	private SecurityController securityController;

	private List<EclihandMenuItemModel> menuItemModels;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3496562499150759806L;

	@PostConstruct
	public void postConstruct() throws Exception {
		initMenuBar();
	}

	@Override
	public void refresh() {
		initMenuBar();
	}

	private void initMenuBar() {
		this.getItems().clear();
		for (EclihandMenuItemModel model : menuItemModels) {
			if (securityController.userHasCredentialIn(model
					.getRequiredCredentials())) {
				this.getItems().add(
						menuItemBuilder.buildMenuItemFor(this, model));
			}
		}
	}

	public List<EclihandMenuItemModel> getMenuItemModels() {
		return menuItemModels;
	}

	public void setMenuItemModels(List<EclihandMenuItemModel> menuItemModels) {
		this.menuItemModels = menuItemModels;
	}

}
