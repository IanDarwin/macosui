package com.darwinsys.macosui;

import java.io.File;
import java.io.IOException;

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

	/** Set a few common properties for the given application if running under MacOS.
	 * <em>Must be invoked before you construct any Swing components</em>, even JFrame.
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

	private static Runtime theRuntime = Runtime.getRuntime();

	/** Handle Apple's continuing failure to make their Java implementation's version
	 * of Runtime.exec() match normal users' behaviour, that is, make Runtime.exec("Foo.app")
	 * actually run "Foo".
	 * @see java.lang.Runtime#exec(java.lang.String)
	 * @param command The commands array
	 * @return The newly-created Process
	 * @throws IOException If anything goes wrong.
	 */
	public Process exec(String[] command) throws IOException {
		File theFile = new File(command[0]);
		if (!theFile.exists()) {
			throw new IOException("Program " + command[0] + " does not exist");
		}
		// Cheesy approach. XXX Should parse Info.plist and get the CFBundleExecutable.
		// This will do for the time being...
		if (theFile.isDirectory() && theFile.getName().endsWith(".app")) {
		    File appDirectory = new File(theFile, "Contents/MacOS");
		    File[] candidates = appDirectory.listFiles();
		    if (candidates.length != 1) {
		        throw new IOException("Can't figure out which file to run");
		    }
		    // The following code was here but obviously does nothing:
		    // theFile = new File(appDirectory, candidates[0].getName());
		}
		// now proceed with native exec
		return theRuntime.exec(command);
	}
}
