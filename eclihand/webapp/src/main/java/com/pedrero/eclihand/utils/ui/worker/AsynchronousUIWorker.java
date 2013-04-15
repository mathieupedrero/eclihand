package com.pedrero.eclihand.utils.ui.worker;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class AsynchronousUIWorker extends Thread {
	private UIAction action;

	private RequestAttributes attributes;

	public AsynchronousUIWorker() {
		super();
		this.attributes = RequestContextHolder.getRequestAttributes();
	}

	@Override
	public void run() {
		RequestContextHolder.setRequestAttributes(attributes);
		action.run();
		RequestContextHolder.resetRequestAttributes();
	}

	public UIAction getAction() {
		return action;
	}

	public void setAction(UIAction action) {
		this.action = action;
	}

}
