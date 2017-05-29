package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JF_login extends JFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel1 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel2 = new JLabel();
    JPasswordField jPasswordField1 = new JPasswordField();
    JPanel jPanel2 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton jBlogin = new JButton();
    JButton jBexit = new JButton();
    JLabel jLabel3 = new JLabel();
    Border border1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
            Color.white, new Color(148, 145, 140));

    public JF_login() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jLabel3.setForeground(Color.red);

        this.setResizable(false);
        this.setTitle("用户登录");
        jTextField1.addKeyListener(new JF_login_jTextField1_keyAdapter(this));
        jPanel1.setBorder(border1);
        jPasswordField1.addKeyListener(new JF_login_jPasswordField1_keyAdapter(this));
        jBexit.addActionListener(new JF_login_jBexit_actionAdapter(this));
        jBlogin.addActionListener(new JF_login_jBlogin_actionAdapter(this));
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        jTextField1.setText("admin");
        jLabel2.setText("用户登录密码：");
        jLabel2.setHorizontalAlignment(JLabel.CENTER);
        jPasswordField1.setText("");
        jPanel2.setLayout(flowLayout1);
        jBlogin.setText("登录");
        jBexit.setText("退出");
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jPasswordField1);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
        jPanel2.add(jLabel3);
        jPanel2.add(jBlogin);
        jPanel2.add(jBexit);
        jLabel1.setText("用户ID：");
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        gridLayout1.setColumns(2);
        gridLayout1.setRows(2);
        jPanel1.setLayout(gridLayout1);
        this.setSize(360,140);

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

    public static void main(String[] args) {
        JF_login jf_login = new JF_login();
    }

    public void jTextField1_keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
            String sqlSelect = null;
            java.util.Vector vdata = null;
            sqlSelect = "select username from tb_user where userid = '" + jTextField1.getText().trim() + "'";
            util.RetrieveObject retrieve = new util.RetrieveObject();
            vdata = retrieve.getObjectRow(sqlSelect);
            System.out.println("vdata " + vdata.size());
            if (vdata.size() > 0){
                jLabel3.setText("当前用户姓名: " + String.valueOf(vdata.get(0)));
                jPasswordField1.requestFocus();
            }else{
                javax.swing.JOptionPane.showMessageDialog(null,"输入的用户ID不存在，请重新输入!!!","系统提示"
                        ,javax.swing.JOptionPane.ERROR_MESSAGE);
                jTextField1.requestFocus();
            }

        }
    }

    public void jPasswordField1_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
           ActionEvent login = new ActionEvent(jBlogin,0,null);
           jBlogin_actionPerformed(login);
        }
    }
    // exit the login
    public void jBexit_actionPerformed(ActionEvent e) {
        int result = javax.swing.JOptionPane.showOptionDialog(null,"是否退出系统登录?","系统提示",
                                  javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.QUESTION_MESSAGE,
                                  null,new String[]  {"是","否"},"否");
       if (result == javax.swing.JOptionPane.YES_OPTION) {
           System.exit(0);
       }

    }

    public void jBlogin_actionPerformed(ActionEvent e) {
        String pass = null;
           pass = String.valueOf(jPasswordField1.getPassword());
           String sqlSelect = null;
           sqlSelect = "select count(*) from tb_user where userid = '" + jTextField1.getText().trim()
                       + "' and pass = '" + pass + "'";
           java.util.Vector vdata = null;
           util.RetrieveObject retrieve = new util.RetrieveObject();
           vdata = retrieve.getObjectRow(sqlSelect);

           if (Integer.parseInt(String.valueOf(vdata.get(0))) > 0){
               AppMain frame = new AppMain();
               this.setVisible(false);
           }else{
               javax.swing.JOptionPane.showMessageDialog(null, "输入的口令不正确,请重新输入!!!", "系统提示",
                                             javax.swing.JOptionPane.ERROR_MESSAGE);
               jPasswordField1.requestFocus();
               return;
           }

    }
}


class JF_login_jBlogin_actionAdapter implements ActionListener {
    private JF_login adaptee;
    JF_login_jBlogin_actionAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBlogin_actionPerformed(e);
    }
}


class JF_login_jPasswordField1_keyAdapter extends KeyAdapter {
    private JF_login adaptee;
    JF_login_jPasswordField1_keyAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }

    public void keyPressed(KeyEvent e) {
        adaptee.jPasswordField1_keyPressed(e);
    }
}


class JF_login_jBexit_actionAdapter implements ActionListener {
    private JF_login adaptee;
    JF_login_jBexit_actionAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}


class JF_login_jTextField1_keyAdapter extends KeyAdapter {
    private JF_login adaptee;
    JF_login_jTextField1_keyAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }

    public void keyPressed(KeyEvent keyEvent) {
        adaptee.jTextField1_keyPressed(keyEvent);
    }
}
