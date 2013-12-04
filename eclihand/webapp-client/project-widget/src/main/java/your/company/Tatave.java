package your.company;

import your.company.client.TataveClientRpc;
import your.company.client.TataveServerRpc;
import your.company.client.TataveState;

import com.vaadin.shared.MouseEventDetails;

// This is the server-side UI component that provides public API 
// for Tatave
public class Tatave extends com.vaadin.ui.AbstractComponent {

	private int clickCount = 0;

	// To process events from the client, we implement ServerRpc
	private TataveServerRpc rpc = new TataveServerRpc() {

		// Event received from client - user clicked our widget
		public void clicked(MouseEventDetails mouseDetails) {
			
			// Send nag message every 5:th click with ClientRpc
			if (++clickCount % 5 == 0) {
				getRpcProxy(TataveClientRpc.class)
						.alert("Ok, that's enough!");
			}
			
			// Update shared state. This state update is automatically 
			// sent to the client. 
			getState().text = "You have clicked " + clickCount + " times";
		}
	};

	public Tatave() {

		// To receive events from the client, we register ServerRpc
		registerRpc(rpc);
	}

	// We must override getState() to cast the state to TataveState
	@Override
	public TataveState getState() {
		return (TataveState) super.getState();
	}
}
