package your.company.client;

import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class TataveWidget extends Label {

	public TataveWidget() {

		// CSS class-name should not be v- prefixed
		setStyleName("project-widget");

		// State is set to widget in TataveConnector		
	}

}