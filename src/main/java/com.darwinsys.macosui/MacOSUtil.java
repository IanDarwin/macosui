package com.darwinsys.macosui;

/** Utilities for GUI work.
 * @version $Id$
 */
public class MacOSUtil {

	/** Return true if we are running MacOS; need a few GUI tweaks if so.
	 * See http://developer.apple.com/technotes/tn2002/tn2110.html.
	 */
	public static boolean isMacOS() {
		return System.getProperty("os.name").toLowerCase().startsWith("mac os x");
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
