package com.darwinsys.macosui;

import javax.swing.*;
import com.apple.eawt.*;

/**
 * The only os-dependant part of com.darwinsys, this is the
 * adapter class to handle MacOS's "different" way of doing About Box,
 * Quit item in App menu, Preferences, and so on.
 */
public class MacOSAppAdapter extends Application {
	ApplicationAdapter appAdapter;
	JFrame  parent;
	AboutBoxHandler abouter;
	PrefsHandler prefser;
	PrintHandler printer;
	ShutdownHandler shutter;

	/** Construct a Handler given the JFrame parent */
	public MacOSAppAdapter(JFrame parent, AboutBoxHandler about,
		PrefsHandler prefs, PrintHandler print, ShutdownHandler shut) {
		appAdapter = new AppEventHandler();
		this.parent = parent;
		abouter = about;
		prefser = prefs;
		if (prefser != null) {
			setEnabledPreferencesMenu(true);
		}
		printer = print;
		shutter = shut;
	}

	/** Method to register this handler with Apple's event manager, calling
	 * addApplicationListener in parent class.
	 */
	public void register() {
		addApplicationListener(appAdapter);
	}

	class AppEventHandler extends ApplicationAdapter {

		/** Construct an AppEventHandler */
		AppEventHandler() {
		}

		/** This is called when the user does Application->About */
		public void handleAbout(ApplicationEvent event) {
			abouter.showAboutBox(parent);
			// event.setIsHandled(true);
		}

		/** Called when the user does Application->Preferences */
		public void handlePreferences(ApplicationEvent event) {
			if (prefser != null)
				prefser.showPrefsDialog(parent);
		}

		public void handlePrint(ApplicationEvent event) {
			if (printer != null)
				printer.doPrint(parent);
		}

		/** This is called when the user does Application->Quit */
		public void handleQuit(ApplicationEvent event) {
			if (shutter != null)
				shutter.shutdown(parent);
			System.exit(0);	// should be notreached
		}
	}
}
