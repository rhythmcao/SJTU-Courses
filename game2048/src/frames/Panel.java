package frames;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *	my own JPanel
 * @author rhythmCao
 *
 */
public class Panel extends JPanel {

	private static final long serialVersionUID = 2639038723215135435L;
	
	/**
	 * digits on current panel
	 */
	private int num = 0;
	/**
	 * Label to show the number
	 */
	private JLabel label;
	
	public Panel() {
		// initialize label
		this.label = new JLabel();
		//add number to show Label
		Font font = new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 50);
		this.label.setFont(font);
		this.add(label);
	}
	
	public Panel(int num) {
		this();
		this.num = num;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
}
