package com.pedrero.eclihand.utils.ui.worker;

public class AsynchronousUIWorker extends Thread {
	private UIAction action;

	@Override
	public void run() {
		action.run();
	}

	public UIAction getAction() {
		return action;
	}

	public void setAction(UIAction action) {
		this.action = action;
	}

}
