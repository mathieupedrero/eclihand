package com.pedrero.eclihand.ui.menubar.menuitem;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

@Component
public class EclihandMenuItemBuilder {

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandNavigator eclihandNavigator;

	public EclihandMenuItemBuilder() {
	}

	public MenuItem buildMenuItemFor(MenuBar menuBar,
			final EclihandMenuItemModel model) {
		return menuBar.new MenuItem(messageResolver.getMessage(model
				.getCaptionKey()), null, new Command() {
			private static final long serialVersionUID = 221385883825946509L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				eclihandNavigator.navigateTo(model.getActionFragment());
			}
		});
	}

}
