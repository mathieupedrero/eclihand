package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.PlayersPanelController;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlayersPanel extends EclihandMainPanel implements Initiable {
	@Resource
	private MessageResolver messageResolver;

	@Resource
	private PlayersPanelController playersPanelController;

	private Layout layout;

	private Label titleLabel;

	private Button searchButton;

	private Button createNewPlayerButton;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954828103989095039L;

	@Override
	public void init() {
		this.setCaption(messageResolver.getMessage("players.panel.title"));
		if (layout == null) {
			layout = eclihandLayoutFactory.createCommonVerticalLayout();
			this.setContent(layout);

			this.titleLabel = eclihandUiFactory.createTitleLabel();
			this.titleLabel.setValue(messageResolver
					.getMessage("players.panel.title"));

			this.searchButton = eclihandUiFactory.createButton();
			this.searchButton.setCaption(messageResolver
					.getMessage("common.find"));

			this.searchButton.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -7117656998497854385L;

				@Override
				public void buttonClick(ClickEvent event) {
					playersPanelController.openPlayerSearchModalWindow();

				}
			});

			this.createNewPlayerButton = eclihandUiFactory.createButton();
			this.createNewPlayerButton.setCaption(messageResolver.getMessage("players.create.new"));

			this.createNewPlayerButton.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -7117656998497854385L;

				@Override
				public void buttonClick(ClickEvent event) {
					playersPanelController.openNewPlayerForm();

				}
			});

			this.layout.addComponent(titleLabel);
			this.layout.addComponent(searchButton);
			this.layout.addComponent(createNewPlayerButton);
		}
	}
}
