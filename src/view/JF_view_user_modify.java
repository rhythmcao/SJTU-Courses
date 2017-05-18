package view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.*;
import javax.swing.DefaultDesktopManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class JF_view_user_modify extends JInternalFrame {
    public JF_view_user_modify() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        buildTable();
        this.getContentPane().setLayout(borderLayout1);
        setSize(380,320);
        this.setClosable(true);
        setVisible(true);
        gridLayout1.setColumns(2);
        gridLayout1.setRows(3);
        jLabel1.setText("用户ID");
        jTextField1.setText("");
        jTextField1.addKeyListener(new
                                   JF_view_user_modify_jTextField1_keyAdapter(this));
        jLabel2.setText("用户姓名");
        jTextField2.setText("");
        jBdel.setText("删除");
        jBdel.addActionListener(new JF_view_user_modify_jBdel_actionAdapter(this));
        jPanel2.setLayout(flowLayout1);
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jBadd.setText("添加");
        jBadd.addActionListener(new JF_view_user_modify_jBadd_actionAdapter(this));
        jBsave.setText("存盘");
        jBsave.addActionListener(new JF_view_user_modify_jButton3_actionAdapter(this));
        jBexit.setText("退出");
        jBexit.addActionListener(new JF_view_user_modify_jBexit_actionAdapter(this));
        jTable1.addMouseListener(new JF_view_user_modify_jTable1_mouseAdapter(this));
        jLabel3.setText("用户口令");
        jTextField3.setText("");
        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jPanel1.setLayout(gridLayout1);
        jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
        jScrollPane1.getViewport().add(jTable1);
        jSplitPane1.add(jPanel1, JSplitPane.BOTTOM);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jTextField2);
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField3);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
        jPanel2.add(jBdel);
        jPanel2.add(jBadd);
        jPanel2.add(jBsave);
        jPanel2.add(jBexit);
        jSplitPane1.setDividerLocation(164);

       // jTextField1.addKeyListener(new SendFocuseAdapter(jTextField2));
        jTextField2.addKeyListener(new SendFocuseAdapter(jTextField3));

        buildTable();
    }

    BorderLayout borderLayout1 = new BorderLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JScrollPane jScrollPane1 = new JScrollPane();
    JPanel jPanel1 = new JPanel();
    JTable jTable1 = new JTable();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel1 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JPanel jPanel2 = new JPanel();
    JButton jBdel = new JButton();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton jBadd = new JButton();
    JButton jBsave = new JButton();
    JButton jBexit = new JButton();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField3 = new JTextField();
    public void buildTable(){
        DefaultTableModel tablemodel = null;
        String[] name = {"用户ID","用户姓名","用户口令"};
        String sqlStr = "select * from tb_user";
        util.RetrieveObject bdt = new  util.RetrieveObject();
        tablemodel = bdt.getTableModel(name,sqlStr);
        jTable1.setModel(tablemodel);
        jTable1.setRowHeight(24);
    }
    public void jBsave_actionPerformed(ActionEvent e) {
         int result = JOptionPane.showOptionDialog(null,"是否存盘用户信息数据?","系统提示",
                                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                    null,new String[]  {"是","否"},"否");
         if (result == JOptionPane.NO_OPTION)  return;

       model.Obj_user objuser = new model.Obj_user();
       objuser.setUserid(jTextField1.getText().trim());
       objuser.setUsername(jTextField2.getText().trim());
       objuser.setPass(jTextField3.getText().trim());
       JdbcAdapter jdbcAdapter = new JdbcAdapter();
       if (jdbcAdapter.InsertOrUpdateObject(objuser)) buildTable();

    }

    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null,"是否退出用户信息维护?","系统提示",
                                   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                   null,new String[]  {"是","否"},"否");
        if (result == JOptionPane.YES_OPTION) {
           manger.closeFrame(this);
        }

    }

    public void jBadd_actionPerformed(ActionEvent e) {
        /*ProduceMaxBh pm = new appstu.util.ProduceMaxBh();
        String sqlStr = null,grdeid = null;
        sqlStr = "SELECT MAX(userid) FROM tb_user";
        grdeid = pm.getMaxBh(sqlStr,"");
        jTextField1.setText(grdeid);*/
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField1.requestFocus();
    }

    public void jTable1_mouseClicked(MouseEvent e) {
        String id = null;
        String sqlStr = null;
        int selectrow = 0;
        selectrow = jTable1.getSelectedRow();
        if (selectrow < 0 ) return;

        id = jTable1.getValueAt(selectrow,0).toString();
        sqlStr = "select * from tb_user where userid = '" + id + "'";
        java.util.Vector vdata = null;
        RetrieveObject retrive = new RetrieveObject();
        vdata = retrive.getObjectRow(sqlStr);

        jTextField1.setText(vdata.get(0).toString());
        jTextField2.setText(vdata.get(1).toString());
        jTextField3.setText(vdata.get(2).toString());

    }

    public void jBdel_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null,"是否删除用户信息数据?","系统提示",
                                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                    null,new String[]  {"是","否"},"否");
         if (result == JOptionPane.NO_OPTION)  return;
         String sqlDel = "delete tb_user where userid = '" + jTextField1.getText().trim() + "'";
         JdbcAdapter jdbcAdapter = new JdbcAdapter();
         if (jdbcAdapter.DeleteObject(sqlDel)) {
             jTextField1.setText("");
             jTextField2.setText("");
             jTextField3.setText("");
             buildTable();
         }
    }

    public void jTextField1_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String id = null;
            id = jTextField1.getText().trim();
            String sqlStr = "select * from tb_user where userid = '" + id + "'";
            java.util.Vector vdata = null;
            RetrieveObject retrive = new RetrieveObject();
            vdata = retrive.getObjectRow(sqlStr);
            System.out.println("vdata.isEmpty() = " + vdata.isEmpty());
            if (!vdata.isEmpty()){
                JOptionPane.showMessageDialog(null,"输入的用户ID已经存在请重新输入!!!","系统提示",JOptionPane.ERROR_MESSAGE);
                jTextField1.requestFocus();
                return;
            }
            jTextField2.requestFocus();

        }
    }
}


class JF_view_user_modify_jTextField1_keyAdapter extends KeyAdapter {
    private JF_view_user_modify adaptee;
    JF_view_user_modify_jTextField1_keyAdapter(JF_view_user_modify adaptee) {
        this.adaptee = adaptee;
    }

    public void keyPressed(KeyEvent e) {
        adaptee.jTextField1_keyPressed(e);
    }
}


class JF_view_user_modify_jTable1_mouseAdapter extends MouseAdapter {
    private JF_view_user_modify adaptee;
    JF_view_user_modify_jTable1_mouseAdapter(JF_view_user_modify adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jTable1_mouseClicked(e);
    }
}


class JF_view_user_modify_jBdel_actionAdapter implements ActionListener {
    private JF_view_user_modify adaptee;
    JF_view_user_modify_jBdel_actionAdapter(JF_view_user_modify adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBdel_actionPerformed(e);
    }
}


class JF_view_user_modify_jBadd_actionAdapter implements ActionListener {
    private JF_view_user_modify adaptee;
    JF_view_user_modify_jBadd_actionAdapter(JF_view_user_modify adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBadd_actionPerformed(e);
    }
}


class JF_view_user_modify_jBexit_actionAdapter implements ActionListener {
    private JF_view_user_modify adaptee;
    JF_view_user_modify_jBexit_actionAdapter(JF_view_user_modify adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}


class JF_view_user_modify_jButton3_actionAdapter implements ActionListener {
    private JF_view_user_modify adaptee;
    JF_view_user_modify_jButton3_actionAdapter(JF_view_user_modify adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {

        adaptee.jBsave_actionPerformed(e);
    }
}
