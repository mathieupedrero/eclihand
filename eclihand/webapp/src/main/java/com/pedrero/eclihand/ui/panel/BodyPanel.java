package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@org.springframework.stereotype.Component(value = "bodyPanel")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BodyPanel extends Panel {

	@Resource(name = "eclihandContentPanel")
	private Panel contentPanel;

	@Resource
	private HomeScreen homePanel;

	@Resource
	private LeftScreen leftScreen;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7526198221763033359L;

	@PostConstruct
	public void postConstruct() {
		layout = eclihandLayoutFactory.createCommonHorizontalLayout();
		this.setContent(layout);
		layout.addComponent(leftScreen);
		layout.addComponent(contentPanel);
		contentPanel.setCaption("TOTO");
		// contentPanel.setContent(homePanel);
		this.setCaption(messageResolver.getMessage("body.caption"));
	}

}
