package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.navigation.AbstractEclihandViewDisplay;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EclihandContentPanel extends AbstractEclihandViewDisplay {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6744360827487818164L;

	@Resource
	private HomeScreen homeScreen;

	@Override
	protected void postConstruct() {
		super.postConstruct();
		this.getMainLayout().addComponent(homeScreen.getComponent());
	}

}
