package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.DefaultDesktopManager;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class JF_view_query_jbqk extends JInternalFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    JPanel jPanel1 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel jLabel1 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel2 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JLabel jLabel3 = new JLabel();
    JComboBox jComboBox3 = new JComboBox();
    JLabel jLabel4 = new JLabel();
    JButton jByes = new JButton();
    JButton jBexit = new JButton();
    JTextField jTextField1 = new JTextField();

    ////////////////
    String tabname = null;
    String zdname = null;
    String ysfname = null;
    String[] jTname = null;
    JLabel jLabel5 = new JLabel();
    public JF_view_query_jbqk() {
        try {
            jbInit();
            initsize();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    private void initsize(){
       jComboBox1.addItem("学生信息");
       jComboBox1.addItem("教师信息");

       jComboBox3.addItem("like");
       jComboBox3.addItem(">");
       jComboBox3.addItem("=");
       jComboBox3.addItem("<");
       jComboBox3.addItem(">=");
       jComboBox3.addItem("<=");

    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jTextField1.setPreferredSize(new Dimension(111, 26));
        jTextField1.addKeyListener(new
                                   JF_view_query_jbqk_jTextField1_keyAdapter(this));
        this.setClosable(true);
        jByes.addActionListener(new JF_view_query_jbqk_jByes_actionAdapter(this));
        jComboBox1.addItemListener(new
                                   JF_view_query_jbqk_jComboBox1_itemAdapter(this));
        jComboBox2.addItemListener(new
                                   JF_view_query_jbqk_jComboBox2_itemAdapter(this));
        jComboBox3.addItemListener(new
                                   JF_view_query_jbqk_jComboBox3_itemAdapter(this));
        jBexit.addActionListener(new JF_view_query_jbqk_jBexit_actionAdapter(this));
        jLabel5.setForeground(Color.red);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTable1);
        jLabel1.setText("查询类型");
        jPanel1.setLayout(flowLayout1);
        jLabel2.setToolTipText("");
        jLabel2.setText("字段");
        jLabel3.setText("运算符");
        jLabel4.setText("数值");
        jByes.setText("确定");
        jBexit.setText("退出");
        jTextField1.setText("");
        jPanel1.add(jLabel1);
        jPanel1.add(jComboBox1);
        jPanel1.add(jLabel2);
        jPanel1.add(jComboBox2);
        jPanel1.add(jLabel3);
        jPanel1.add(jComboBox3);
        jPanel1.add(jLabel4);
        jPanel1.add(jTextField1);
        jPanel1.add(jByes);
        jPanel1.add(jBexit);
        this.getContentPane().add(jLabel5, java.awt.BorderLayout.SOUTH);

        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        this.setSize(680,460);
        this.setVisible(true);
    }

    public void jByes_actionPerformed(ActionEvent e) {
        String sqlSelect = null,whereSql = null;
        String valueStr = jTextField1.getText().trim();

        sqlSelect =  this.tabname;
        /*SELECT s.stuid, c.className, s.stuname, s.sex, s.age, s.addr, s.phone
FROM tb_studentinfo s ,tb_classinfo c where s.classID = c.classID */

        if (ysfname == "like" ){
            whereSql = " and " + this.zdname + " " + this.ysfname + " '%" +  valueStr + "%'";
        }else{
            whereSql = " and " + this.zdname + " " + this.ysfname + " '" +  valueStr + "'";
        }
        util.RetrieveObject retrieve = new util.RetrieveObject();
        javax.swing.table.DefaultTableModel defaultmodel = null;
        defaultmodel = retrieve.getTableModel(jTname,sqlSelect + whereSql);
        jTable1.setModel(defaultmodel);
        if (jTable1.getRowCount() <= 0 ){
            JOptionPane.showMessageDialog(null,"没有找到满足条件的数据!!!","系统提示",JOptionPane.INFORMATION_MESSAGE);
        }
        jTable1.setRowHeight(24);
        jLabel5.setText("共有数据【" + String.valueOf(jTable1.getRowCount())+ "】条");
    }

    public void jComboBox1_itemStateChanged(ItemEvent itemEvent) {
        if (jComboBox1.getSelectedIndex() == 0){
            this.tabname = "SELECT s.stuid, c.className, s.stuname, s.sex, s.age, s.addr, s.phone FROM tb_studentinfo s ,tb_classinfo c where s.classID = c.classID";
            String[] name = {"学生编号","班级名称","学生姓名","性别","年龄","家庭住址","联系电话"};
            jTname = name;
            jComboBox2.removeAllItems();
            jComboBox2.addItem("学生编号");
            jComboBox2.addItem("班级编号");

        }if(jComboBox1.getSelectedIndex() == 1){
            this.tabname = "SELECT t.teaid, c.className, t.teaname, t.sex, t.knowledge, t.knowlevel FROM tb_teacher t INNER JOIN tb_classinfo c ON c .classID = t.classID";
            String[] name = {"教师编号","班级名称","教师姓名","性别","教师职称","教师等级"};
            jTname = name;
            jComboBox2.removeAllItems();
            jComboBox2.addItem("教师编号");
            jComboBox2.addItem("班级编号");
        }

        System.out.println("tabname = " + tabname);
    }

    public void jComboBox2_itemStateChanged(ItemEvent itemEvent) {
        if (jComboBox1.getSelectedIndex() == 0){
            if (jComboBox2.getSelectedIndex() == 0) this.zdname = "s.stuid";
            if (jComboBox2.getSelectedIndex() == 1) this.zdname = "s.classID";
        }if(jComboBox1.getSelectedIndex() == 1){
            if (jComboBox2.getSelectedIndex() == 0) this.zdname = "t.teaid";
            if (jComboBox2.getSelectedIndex() == 1) this.zdname = "t.classID";
        }
        System.out.println("zdname = " + zdname);
    }

    public void jComboBox3_itemStateChanged(ItemEvent itemEvent) {
        this.ysfname = String.valueOf(jComboBox3.getSelectedItem());
        System.out.println("ysfname = " + ysfname);
    }

    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null,"是否退出基本信息情况查询?","系统提示",
                                   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                   null,new String[]  {"是","否"},"否");
        if (result == JOptionPane.YES_OPTION) {
           manger.closeFrame(this);
        }

    }

    public void jTextField1_keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
            ActionEvent event = new ActionEvent(jByes,0,null);
            this.jByes_actionPerformed(event);
        }
    }


}


class JF_view_query_jbqk_jTextField1_keyAdapter extends KeyAdapter {
    private JF_view_query_jbqk adaptee;
    JF_view_query_jbqk_jTextField1_keyAdapter(JF_view_query_jbqk adaptee) {
        this.adaptee = adaptee;
    }

    public void keyPressed(KeyEvent keyEvent) {
        adaptee.jTextField1_keyPressed(keyEvent);
    }
}


class JF_view_query_jbqk_jBexit_actionAdapter implements ActionListener {
    private JF_view_query_jbqk adaptee;
    JF_view_query_jbqk_jBexit_actionAdapter(JF_view_query_jbqk adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.jBexit_actionPerformed(actionEvent);
    }
}


class JF_view_query_jbqk_jByes_actionAdapter implements ActionListener {
    private JF_view_query_jbqk adaptee;
    JF_view_query_jbqk_jByes_actionAdapter(JF_view_query_jbqk adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.jByes_actionPerformed(actionEvent);
    }
}


class JF_view_query_jbqk_jComboBox1_itemAdapter implements ItemListener {
    private JF_view_query_jbqk adaptee;
    JF_view_query_jbqk_jComboBox1_itemAdapter(JF_view_query_jbqk adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        adaptee.jComboBox1_itemStateChanged(itemEvent);
    }
}


class JF_view_query_jbqk_jComboBox2_itemAdapter implements ItemListener {
    private JF_view_query_jbqk adaptee;
    JF_view_query_jbqk_jComboBox2_itemAdapter(JF_view_query_jbqk adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        adaptee.jComboBox2_itemStateChanged(itemEvent);
    }
}


class JF_view_query_jbqk_jComboBox3_itemAdapter implements ItemListener {
    private JF_view_query_jbqk adaptee;
    JF_view_query_jbqk_jComboBox3_itemAdapter(JF_view_query_jbqk adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        adaptee.jComboBox3_itemStateChanged(itemEvent);
    }
}
