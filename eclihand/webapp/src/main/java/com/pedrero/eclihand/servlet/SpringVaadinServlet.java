package com.pedrero.eclihand.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.server.VaadinServlet;

public class SpringVaadinServlet extends VaadinServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 630737907185236356L;

	public static WebApplicationContext WEB_APPLICATION_CONTEXT;

	@Override
	public void init(ServletConfig config) throws ServletException {

		WEB_APPLICATION_CONTEXT = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());

		super.init(config);

	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		super.service(request, response);
	}

	// @Override
	// protected Application getNewApplication(HttpServletRequest request)
	// throws ServletException {
	// Application newOne = webApplicationContext.getBean(clazz);
	// newOne.setLocale(request.getLocale());
	// LocaleContainer localeContainer = webApplicationContext
	// .getBean(LocaleContainer.class);
	// localeContainer.setLocale(request.getLocale());
	// return newOne;
	// }
	//
	// @Override
	// protected Class<? extends Application> getApplicationClass()
	// throws ClassNotFoundException {
	// return clazz;
	// }

}
