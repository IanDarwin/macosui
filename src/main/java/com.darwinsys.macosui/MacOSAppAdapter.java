package com.darwinsys.macosui;

import javax.swing.*;
import com.apple.eawt.*;

import jabagator.JabaGator;
import jabagator.JBView;

/**
 * The only os-dependant part of com.darwinsys, this is the
 * adapter class to handle MacOS's "different" way of doing About Box,
 * Quit item in App menu, Preferences, and so on.
 */
public class MacOSAppHandler extends Application {
	ApplicationAdapter appAdapter;
	JFrame  parent;
	AboutBoxHandler abouter;
	PrefsHandler prefser;
	ShutdownHandler shutter;

	/** Construct a Handler given the JFrame parent */
	public MacOSAppHandler(JFrame parent, AboutBoxHandler about,
		PrefsHandler prefs, ShutdownHandler shut) {
		appAdapter = new AppEventHandler(parent);
		this.parent = parent;
		abouter = about;
		prefser = prefs;
		shutter = shut;
	}

	/** Method to register this handler with Apple's event manager, calling
	 * addApplicationListener in parent class.
	 */
	public void register() {
		addApplicationListener(handler);
	}

	class AppEventHandler extends ApplicationAdapter {

		/** Construct an AppEventHandler */
		AppEventHandler() {
		}

		/** This is called when the user does Application->About */
		public void handleAbout(ApplicationEvent event) {
			abouter.showAboutBox(parent);
		}

		/** Called when the user does Application->Preferences */
		public void handlePreferences(ApplicationEvent event) {
			prefser.showPrefsDialog(parent);
		}

		/** This is called when the user does Application->Quit */
		public void handleQuit(ApplicationEvent event) {
			shutter.doShutdown(parent);
		}
	}
}
