package com.pedrero.eclihand.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

public class SpringVaadinServlet extends AbstractApplicationServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 630737907185236356L;

	private Class<? extends Application> clazz;

	private WebApplicationContext webApplicationContext;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		webApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());

		Application application = webApplicationContext
				.getBean(Application.class);
		clazz = application.getClass();
	}

	@Override
	protected Application getNewApplication(HttpServletRequest request)
			throws ServletException {
		Application newOne = webApplicationContext.getBean(clazz);
		newOne.setLocale(request.getLocale());
		LocaleContainer localeContainer = webApplicationContext
				.getBean(LocaleContainer.class);
		localeContainer.setLocale(request.getLocale());
		return newOne;
	}

	@Override
	protected Class<? extends Application> getApplicationClass()
			throws ClassNotFoundException {
		return clazz;
	}

}
