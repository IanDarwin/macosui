package com.darwinsys.macosui;

import java.awt.*;

/** Utilities for GUI work.
 * @version $Id$
 */
public class MacOSUtil {

	/** Return true if we are running MacOS; needs a few GUI tweaks if so. */
	public static boolean isMacOS() {
		return System.getProperty("mrj.version") != null;
	}

	/** Set a few common properties for the given application if
	 * we are running under MacOS.
	 * Usage Example:
	 * <pre>
	 *	if (MacOSUtil.isMacOS()) {
	 *		MacOSUtil.setMacOS("JabaDex");
	 *	}
	 * </pre>
	 * @param appName - the name of the Application.
	 */
	public static void setMacOS(String appName) {
		System.setProperty("apple.laf.useScreenMenuBar",  "true");
		System.setProperty("apple.awt.showGrowBox",  "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
			appName);
	}
}
