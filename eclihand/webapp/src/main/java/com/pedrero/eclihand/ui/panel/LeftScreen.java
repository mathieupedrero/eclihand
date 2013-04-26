package com.pedrero.eclihand.ui.panel;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.LeftPanelController;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LeftScreen extends Panel implements Initiable {
	@Resource
	private LeftPanelController leftPanelController;

	@Value(value = "${left.panel.width}")
	private String panelWidth;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3108067403737976755L;

	@Override
	public void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		layout.setWidth(panelWidth);
		this.setContent(layout);
		this.setCaption(Locale.FRANCE.toString());
	}

}
