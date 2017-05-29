package util;

import java.awt.event.KeyAdapter;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
public class SendFocuseAdapter extends KeyAdapter {
    private JTextField jTextField = null;
    private JComboBox jComboBox = null;
    public SendFocuseAdapter(JTextField ss) {
        this.jTextField = ss;
    }
    public SendFocuseAdapter(JComboBox ss) {
        this.jComboBox = ss;
    }

    public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_ENTER){
        if(jTextField instanceof JTextField){
            this.jTextField.requestFocus();
        }
        if (jComboBox instanceof JComboBox){
            this.jComboBox.requestFocus();
        }
    }
  }
}
