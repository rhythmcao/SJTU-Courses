
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;
import util.CommonaJdbc;
import view.JF_login;

public class AppStu {
	boolean packFrame = false;

	/**
	 * Construct and show the application.
	 */
	public AppStu() {
		CommonaJdbc Jdbc = new CommonaJdbc();
		if (Jdbc != null) {
			JF_login frame = new JF_login();
		}

		// AppMain frame = new AppMain();
		// Validate frames that have preset sizes
		// Pack frames that have useful preferred size info, e.g. from their
		// layout
		/*
		 * if (packFrame) { frame.pack(); } else { frame.validate(); }
		 * 
		 * // Center the window Dimension screenSize =
		 * Toolkit.getDefaultToolkit().getScreenSize(); Dimension frameSize =
		 * frame.getSize(); if (frameSize.height > screenSize.height) {
		 * frameSize.height = screenSize.height; } if (frameSize.width >
		 * screenSize.width) { frameSize.width = screenSize.width; }
		 * frame.setLocation((screenSize.width - frameSize.width) / 2,
		 * (screenSize.height - frameSize.height) / 2); frame.setVisible(true);
		 */
	}

	/**
	 * Application entry point.
	 *
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		CommonaJdbc Jdbc = new CommonaJdbc();

		if (Jdbc.conection != null) {
			JF_login frame = new JF_login();
		}

		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { try {
		 * //
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (Exception exception) { exception.printStackTrace(); }
		 * 
		 * new AppStu(); } });
		 */
	}
}
