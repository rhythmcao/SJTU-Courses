
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
		if (Jdbc.conection != null) {
			JF_login frame = new JF_login();
		}
	}

	/**
	 * Application entry point.
	 *
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new AppStu();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    });
	}
}
