package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.HomePanelController;
import com.pedrero.eclihand.utils.Displayer;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomeScreen extends EclihandAbstractComponent implements Initiable, Displayer {

	@Resource
	private HomePanelController homePanelController;

	@Resource
	private MessageResolver messageResolver;

	@Value(value = "${main.panel.width}")
	private String panelWidth;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = -499208820273916658L;

	@Override
	public void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		layout.setWidth(panelWidth);
		this.addComponent(layout);
		this.setCaption(messageResolver.getMessage("home.caption"));

	}

	@Override
	public void display() {

	}

}
