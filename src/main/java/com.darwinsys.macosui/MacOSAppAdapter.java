package jabagator.swingui;

import javax.swing.*;
import com.apple.eawt.*;

/**
 * Adapter class to handle MacOS's "different" way of doing About Box,
 * Quit item in App menu, and so on.
 */
public class MacOSAppHandler extends Application {
	ApplicationAdapter handler;

	/** Construct a Handler given the JFrame parent */
	public MacOSAppHandler(JFrame parent) {
		handler = new AppEventHandler(parent);
	}

	/** Method to register this handler with Apple's event manager */
	public void register() {
		addApplicationListener(handler);
	}

	class AppEventHandler extends ApplicationAdapter {
		JFrame parent;

		/** Construct an AppEventHandler, given its parent JFrame */
		AppEventHandler(JFrame parent) {
			this.parent = parent;
		}

		/** This is called when the user does Application->About */
		public void handleAbout(ApplicationEvent event) {
			new AboutBox(parent).setVisible(true);
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
