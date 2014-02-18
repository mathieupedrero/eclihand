package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.ui.IFrameElement;
import com.pedrero.eclihand.ui.menubar.MainMenuBar;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HeaderPanel extends Panel implements Initiable, IFrameElement {

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private BeanFactory beanFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3099650159349626440L;

	@PostConstruct
	public void postConstruct() {
		this.setCaption(messageResolver.getMessage("header.caption"));
		defineLayout();
	}

	private void defineLayout() {
		Layout layout = eclihandLayoutFactory.createCommonHorizontalLayout();
		this.setContent(layout);
		layout.addComponent(beanFactory.getBean(MainMenuBar.class));
	}

	@Override
	public void refresh() {
		defineLayout();
	}

}
