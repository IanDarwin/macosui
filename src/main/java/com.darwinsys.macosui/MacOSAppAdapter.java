package jabagator.macosui;

import javax.swing.*;
import com.apple.eawt.*;

import jabagator.JabaGator;
import jabagator.JBView;

/**
 * Adapter class to handle MacOS's "different" way of doing About Box,
 * Quit item in App menu, and so on.
 */
public class MacOSAppHandler extends Application {
	ApplicationAdapter handler;
	JBView parent;

	/** Construct a Handler given the JFrame parent */
	public MacOSAppHandler(JBView parent) {
		handler = new AppEventHandler(parent);
		this.parent = parent;
	}

	/** Method to register this handler with Apple's event manager */
	public void register() {
		addApplicationListener(handler);
	}

	class AppEventHandler extends ApplicationAdapter {
		JBView parent;

		/** Construct an AppEventHandler, given its parent JFrame */
		AppEventHandler(JBView parent) {
			this.parent = parent;
		}

		/** This is called when the user does Application->About */
		public void handleAbout(ApplicationEvent event) {
			parent.showAboutBox();
		}

		/** Called when the user does Application->Preferences */
		public void handlePreferences(ApplicationEvent event) {
			JOptionPane.showMessageDialog(parent,
				"Preferences", "TODO: Need to hook preferences dialog here",
				JOptionPane.INFORMATION_MESSAGE);
		}

		/** This is called when the user does Application->Quit */
		public void handleQuit(ApplicationEvent event) {
			System.out.println("TODO write code to check for unsaved changes");
			System.exit(0);
		}
	}
}
