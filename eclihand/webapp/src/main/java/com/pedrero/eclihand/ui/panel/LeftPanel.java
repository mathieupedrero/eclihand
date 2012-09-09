package com.pedrero.eclihand.ui.panel;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.LeftPanelController;
import com.pedrero.eclihand.utils.Initiable;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LeftPanel extends Panel implements Initiable {
	@Resource
	private LeftPanelController leftPanelController;

	@Value(value = "${left.panel.width}")
	private String panelWidth;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3108067403737976755L;

	@Override
	public void init() {
		layout = new VerticalLayout();
		layout.setWidth(panelWidth);
		this.setContent(layout);
		this.setCaption(Locale.FRANCE.toString());
	}

}
