package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.ui.IFrameElement;
import com.pedrero.eclihand.ui.login.LoginPanel;
import com.pedrero.eclihand.ui.login.LogoutPanel;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LeftScreen extends Panel implements IFrameElement {

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private LoginPanel loginPanel;

	@Resource
	private LogoutPanel logoutPanel;

	@Resource
	private MessageResolver messageResolver;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3108067403737976755L;

	private void prepareContent() {
		Layout layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);
		layout.addComponent(loginPanel.getWrapperLayout());
		layout.addComponent(logoutPanel.getWrapperLayout());
		this.setCaption(messageResolver.getMessage("left.caption"));
	}

	@PostConstruct
	public void postConstruct() throws Exception {
		prepareContent();
	}

	@Override
	public void refresh() {
		prepareContent();
	}

}
