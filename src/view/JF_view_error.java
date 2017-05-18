package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public class JF_view_error extends JFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    JTextPane jTextPane1 = new JTextPane();
    String errorInfo = null;
    Border border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,
            Color.red, Color.red, Color.magenta, Color.magenta);
    Border border2 = new TitledBorder(border1, "操作数据库错误信息");
    JPanel jPanel1 = new JPanel();
    JButton jButton1 = new JButton();
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel jLabel1 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton2 = new JButton();
    public JF_view_error(String error) {
        this.errorInfo = error;
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jTextPane1.setBorder(border2);
        jTextPane1.setText(this.errorInfo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("系统提示");
        jButton1.setText("关闭");
        jButton1.addActionListener(new JF_view_error_jButton1_actionAdapter(this));
        jPanel1.setLayout(flowLayout1);
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jButton2.setText("终止程序");
        jButton2.addActionListener(new JF_view_error_jButton2_actionAdapter(this));
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
        jPanel1.add(jButton2);
        jPanel1.add(jButton1);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        this.getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.getViewport().add(jTextPane1);
        this.setSize(400,300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);

        this.setVisible(true);
    }

    public static void main(String args[]){
        JF_view_error error = new JF_view_error("哈哈，哈你来了");
    }
    public void jButton1_actionPerformed(ActionEvent e) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}


class JF_view_error_jButton2_actionAdapter implements ActionListener {
    private JF_view_error adaptee;
    JF_view_error_jButton2_actionAdapter(JF_view_error adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class JF_view_error_jButton1_actionAdapter implements ActionListener {
    private JF_view_error adaptee;
    JF_view_error_jButton1_actionAdapter(JF_view_error adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
