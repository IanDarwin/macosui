package regress;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.darwinsys.macosui.AboutBoxHandler;
import com.darwinsys.macosui.MacOSAppAdapter;
import com.darwinsys.macosui.MacOSUtil;
import com.darwinsys.macosui.PrefsHandler;
import com.darwinsys.macosui.PrintHandler;
import com.darwinsys.macosui.ShutdownHandler;

/**
 * @author ian
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MacOSUITest {

	public static void main(String[] args) {
		MacOSUtil.setMacOS("MacOSUITest");
		new MacOSUITest();

	}
	
	public MacOSUITest() {
		JFrame jf = new JFrame("MacOSUITest");
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		jf.getContentPane().add(button);
		MacOSAppAdapter adapter =
			new MacOSAppAdapter(jf, abouter, prefser, printer, shutter);
		adapter.register();
		jf.setSize(300, 200);
		jf.setVisible(true);
	}

	
	AboutBoxHandler abouter = new AboutBoxHandler() {
		public void showAboutBox(JFrame jf) {
			JOptionPane.showMessageDialog(
				null,
				"About Box",
				"About Box",
				JOptionPane.INFORMATION_MESSAGE);

		};
	};
	PrefsHandler prefser = new PrefsHandler() {
		public void showPrefsDialog(JFrame jf) {
			JOptionPane.showMessageDialog(
				null,
				"Preferences Box",
				"Preferences Box",
				JOptionPane.INFORMATION_MESSAGE);
		};
	};
	PrintHandler printer = new PrintHandler() {
		public void doPrint(JFrame jf) {
			JOptionPane.showMessageDialog(
				null,
				"Print Handler",
				"Print Handler",
				JOptionPane.INFORMATION_MESSAGE);
		}
	};
	ShutdownHandler shutter = new ShutdownHandler() {
		public void shutdown(JFrame jf) {
			JOptionPane.showMessageDialog(
				null,
				"Shutdown",
				"Shutdown",
				JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		};
	};

}
