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
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomePanel extends Panel implements Initiable, Displayer {

	@Resource
	private HomePanelController homePanelController;

	@Resource
	private MessageResolver messageResolver;

	@Value(value = "${main.panel.width}")
	private String panelWidth;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = -499208820273916658L;

	@Override
	public void init() {
		layout = new VerticalLayout();
		layout.setWidth(panelWidth);
		this.setContent(layout);
		this.setCaption(messageResolver.getMessage("home.caption"));

	}

	@Override
	public void display() {

	}

}
