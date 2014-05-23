package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.navigation.EclihandNavigator;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.EclihandViewImpl;
import com.pedrero.eclihand.navigation.places.PlayerPlace;
import com.pedrero.eclihand.navigation.places.PlayersPlace;
import com.pedrero.eclihand.service.PlayerService;
import com.pedrero.eclihand.utils.spring.EclihandBeanFactory;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.pedrero.eclihand.utils.ui.UICallback;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

@Component(value = "playersScreen")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class PlayersScreen extends EclihandViewImpl {

	@Resource
	private MessageResolver messageResolver;

	private Label titleLabel;

	private Button searchButton;

	private Button createNewPlayerButton;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private PlayersPlace playersPlace;

	@Resource
	private PlayerPlace playerPlace;

	@Resource
	private EclihandBeanFactory beanFactory;

	@Resource
	private PlayerService playerService;

	@Resource
	private EclihandNavigator navigator;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954828103989095039L;

	@PostConstruct
	protected void postConstruct() {
		this.setCaption(messageResolver.getMessage("players.panel.title"));

		Layout layout = eclihandLayoutFactory.createCommonVerticalLayout();

		Label label = eclihandUiFactory.createLabel();
		label.setValue("Players");
		layout.addComponent(label);

		this.setContent(layout);

		this.titleLabel = eclihandUiFactory.createTitleLabel();
		this.titleLabel.setValue(messageResolver
				.getMessage("players.panel.title"));

		this.searchButton = eclihandUiFactory.createButton();
		this.searchButton.setCaption(messageResolver.getMessage("common.find"));

		this.searchButton.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = -7117656998497854385L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().addWindow(
						(Window) beanFactory.getBean("playerSearchModalWindow",
								new UICallback<PlayerDto>() {

									@Override
									public void execute(PlayerDto dataObject) {
										playerPlace.setId(dataObject.getId());
										navigator.navigateTo(playerPlace);
									}
								}, playerService));

			}
		});

		this.createNewPlayerButton = eclihandUiFactory.createButton();
		this.createNewPlayerButton.setCaption(messageResolver
				.getMessage("players.create.new"));

		this.createNewPlayerButton.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = -7117656998497854385L;

			@Override
			public void buttonClick(ClickEvent event) {
				// playersPanelController.openNewPlayerForm();

			}
		});

		layout.addComponent(titleLabel);
		layout.addComponent(searchButton);
		layout.addComponent(createNewPlayerButton);
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return playersPlace;
	}
}
