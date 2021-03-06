package com.pedrero.eclihand.navigation;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EcliViewProvider implements ViewProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(EcliViewProvider.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 7569617022445501031L;

	@Resource
	private BeanFactory beanFactory;

	private String viewName;

	private Class<? extends View> viewClass;

	@Override
	public String getViewName(String navigationState) {
		if (null == navigationState) {
			return null;
		}
		if (navigationState.equals(viewName) || navigationState.startsWith(viewName + "/")) {
			return viewName;
		}
		return null;
	}

	@Override
	public View getView(String viewName) {
		LOGGER.debug("view initialization for [{}]", viewName);
		if (this.viewName.equals(viewName)) {
			View view = beanFactory.getBean(viewClass);
			return view;
		}
		return null;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Class<? extends View> getViewClass() {
		return viewClass;
	}

	public void setViewClass(Class<? extends View> viewClass) {
		this.viewClass = viewClass;
	}

}
