package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import util.RetrieveObject;
import util.SendFocuseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.DefaultDesktopManager;
import util.ProduceMaxBh;
import javax.swing.table.DefaultTableModel;
import util.JdbcAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JF_view_student extends JInternalFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JPanel jPanel1 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel jLabel1 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel2 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JButton jBadd = new JButton();
    JButton jBsave = new JButton();
    JPanel jPanel3 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JLabel jLabel5 = new JLabel();
    JTextField jTextField3 = new JTextField();
    JLabel jLabel6 = new JLabel();
    JTextField jTextField4 = new JTextField();
    JLabel jLabel7 = new JLabel();
    JComboBox jComboBox3 = new JComboBox();
    JLabel jLabel8 = new JLabel();
    JTextField jTextField5 = new JTextField();
    JLabel jLabel9 = new JLabel();
    JTextField jTextField6 = new JTextField();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    JButton jBrefresh = new JButton();
    JButton jBexit = new JButton();
    JButton jBdel = new JButton();

    String gradeID[] = null;
    String classID[] = null;
    public JF_view_student() {
        try {
            jbInit();
            initialize();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setOpaque(false);
        jSplitPane1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(flowLayout1);
        jLabel1.setText("所属年级:");
        jLabel2.setToolTipText("");
        jLabel2.setText("所属班级:");
        jBadd.setText("添加");
        jBadd.addActionListener(new JF_view_student_jBadd_actionAdapter(this));
        jBsave.setMnemonic('0');
        jBsave.setText("存盘");
        jBsave.addActionListener(new JF_view_student_jBsave_actionAdapter(this));
        jPanel3.setLayout(gridLayout1);
        gridLayout1.setColumns(4);
        gridLayout1.setRows(4);
        jLabel3.setText("学生编号");
        jTextField1.setEnabled(false);
        jTextField1.setText("");
        jLabel4.setText("班级名称");
        jTextField2.setEnabled(false);
        jTextField2.setText("");
        jLabel5.setText("学生姓名");
        jTextField3.setText("");
        jLabel6.setText("年龄");
        jTextField4.setText("");
        jLabel7.setText("性别");
        jLabel8.setText("家庭地址");
        jTextField5.setText("");
        jLabel9.setText("联系电话");
        jTextField6.setText("");
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.
                                                  HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.
                                                VERTICAL_SCROLLBAR_ALWAYS);
        jBrefresh.setText("刷新");
        jBrefresh.addActionListener(new JF_view_student_jBrefresh_actionAdapter(this));
        jBexit.setText("退出");
        jBexit.addActionListener(new JF_view_student_jBexit_actionAdapter(this));
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        this.setClosable(true);
        jBdel.setText("删除");
        jBdel.addActionListener(new JF_view_student_jBdel_actionAdapter(this));
        jTable1.addMouseListener(new JF_view_student_jTable1_mouseAdapter(this));
        jComboBox1.addItemListener(new JF_view_student_jComboBox1_itemAdapter(this));
        jComboBox2.addItemListener(new JF_view_student_jComboBox2_itemAdapter(this));
        jPanel1.add(jLabel1);
        jPanel1.add(jComboBox1);
        jPanel1.add(jLabel2);
        jPanel1.add(jComboBox2);
        jPanel1.add(jBrefresh);
        jPanel1.add(jBadd);
        jPanel1.add(jBdel);
        jPanel1.add(jBsave);
        jPanel1.add(jBexit);
        jSplitPane1.add(jPanel3, JSplitPane.BOTTOM);
        jPanel3.add(jLabel3);
        jPanel3.add(jTextField1);
        jPanel3.add(jLabel4);
        jPanel3.add(jTextField2);
        jPanel3.add(jLabel5);
        jPanel3.add(jTextField3);
        jPanel3.add(jLabel6);
        jPanel3.add(jTextField4);
        jPanel3.add(jLabel7);
        jPanel3.add(jComboBox3);
        jPanel3.add(jLabel9);
        jPanel3.add(jTextField5);
        jPanel3.add(jLabel8);
        jPanel3.add(jTextField6);
        jTextField3.addKeyListener(new SendFocuseAdapter(jTextField4));
        jTextField4.addKeyListener(new SendFocuseAdapter(jTextField5));
        jTextField5.addKeyListener(new SendFocuseAdapter(jTextField6));
        //jTextField6.addKeyListener(new SendFocuseAdapter(jTextField7));
        jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTable1);
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        setSize(640,440);
        setVisible(true);
        jSplitPane1.setDividerLocation(220);
    }
    public void initialize(){
        String sqlStr = null;
        sqlStr  = "select gradeID,gradeName from tb_gradeinfo";

        RetrieveObject retrieve = new RetrieveObject();
        java.util.Collection collection = null;
        java.util.Iterator iterator = null;
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        gradeID = new String[collection.size()];
        int i = 0;
        while(iterator.hasNext()){
          java.util.Vector vdata = (java.util.Vector)iterator.next();
          gradeID[i] = String.valueOf(vdata.get(0));
          jComboBox1.addItem(vdata.get(1));
          i++;
        }

        /**/
    }

//添加一条数据
    public void jBadd_actionPerformed(ActionEvent e) {
        /*String classid = "0" + String.valueOf(jComboBox1.getSelectedIndex() + 1)
                         + "0" + String.valueOf(jComboBox2.getSelectedIndex() + 1);*/
        String classid = null;
        int index = jComboBox2.getSelectedIndex();
        if (index < 0){
            JOptionPane.showMessageDialog(null,"班级名称为空,请重新选择班级","系统提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        classid = classID[index];
        String sqlMax = "select max(stuid) from tb_studentinfo where classID = '" + classid + "'";

        ProduceMaxBh pm = new util.ProduceMaxBh();

        String stuid = null;
        stuid = pm.getMaxBh(sqlMax,classid);

        jTextField1.setText(stuid);
        //jTextField2.setText(jComboBox1.getSelectedItem().toString() + jComboBox2.getSelectedItem().toString());
        jTextField2.setText(jComboBox2.getSelectedItem().toString());
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox3.removeAllItems();
        jComboBox3.addItem("男");
        jComboBox3.addItem("女");

        jTextField3.requestFocus();

    }

    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null,"是否退出学生基本信息录入?","系统提示",
                                   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                   null,new String[]  {"是","否"},"否");
        if (result == JOptionPane.YES_OPTION) {
           manger.closeFrame(this);
        }

    }

    public void jBsave_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null,"是否存盘学生基本数据信息?","系统提示",
                                   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                   null,new String[]  {"是","否"},"否");
        if (result == JOptionPane.NO_OPTION) return;

        model.Obj_student object = new model.Obj_student();
        /*String classid = "0" + String.valueOf(jComboBox1.getSelectedIndex() + 1)
                         + "0" + String.valueOf(jComboBox2.getSelectedIndex() + 1);*/
        String classid = classID[Integer.parseInt(String.valueOf(jComboBox2.getSelectedIndex()))];
        object.setStuid(jTextField1.getText().trim());
        object.setClassID(classid);
        object.setStuname(jTextField3.getText().trim());
        int age = 0;
        try{
            age = Integer.parseInt(jTextField4.getText().trim());
        }catch(java.lang.NumberFormatException formate){
            JOptionPane.showMessageDialog(null,"数据录入有误，错误信息:\n" + formate.getMessage(), "系统提示",JOptionPane.ERROR_MESSAGE);
            jTextField4.requestFocus();
            return;
        }

        object.setAge(age);
        object.setSex(String.valueOf(jComboBox3.getSelectedItem()));
        object.setPhone(jTextField5.getText().trim());
        object.setAddress(jTextField6.getText().trim());

        util.JdbcAdapter adapter = new util.JdbcAdapter();
        if (adapter.InsertOrUpdateObject(object)){
            ActionEvent event = new ActionEvent(jBrefresh,0,null);
            jBrefresh_actionPerformed(event);
        }
    }

    public void jBrefresh_actionPerformed(ActionEvent e) {
       DefaultTableModel tablemodel = null;
       String[] name = {"学生编号","班级编号","学生姓名","性别","年龄","家庭住址","联系电话"};
       String sqlStr = "select * from tb_studentinfo";
       util.RetrieveObject bdt = new  util.RetrieveObject();
       tablemodel = bdt.getTableModel(name,sqlStr);
       jTable1.setModel(tablemodel);
       jTable1.setRowHeight(24);

    }

    public void jBdel_actionPerformed(ActionEvent e) {
         if (jTextField1.getText().trim().length() <= 0) return;

         int result = JOptionPane.showOptionDialog(null,"是否删除学生的基本信息数据?","系统提示",
                                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                                    null,new String[]  {"是","否"},"否");
         if (result == JOptionPane.NO_OPTION)  return;

         String sqlDel = "delete tb_studentinfo where stuid = '" + jTextField1.getText().trim() + "'";
         JdbcAdapter jdbcAdapter = new JdbcAdapter();
         if (jdbcAdapter.DeleteObject(sqlDel)) {
             jTextField1.setText("");
             jTextField2.setText("");
             jTextField3.setText("");
             jTextField4.setText("");
             jTextField5.setText("");
             jTextField6.setText("");
             jComboBox1.removeAllItems();
             jComboBox3.removeAllItems();
             ActionEvent event = new ActionEvent(jBrefresh,0,null);
             jBrefresh_actionPerformed(event);

         }

    }

    public void jTable1_mouseClicked(MouseEvent e) {
        String id = null;
        String sqlStr = null;
        int selectrow = 0;
        selectrow = jTable1.getSelectedRow();
        if (selectrow < 0 ) return;

        id = jTable1.getValueAt(selectrow,0).toString();
        sqlStr = "select * from tb_studentinfo where stuid = '" + id + "'";
        java.util.Vector vdata = null;
        RetrieveObject retrive = new RetrieveObject();
        vdata = retrive.getObjectRow(sqlStr);
        String gradeid = null,classid = null;
        String gradename = null,classname = null;
        java.util.Vector vname = null;
        classid = vdata.get(1).toString();
        gradeid = classid.substring(0,2);
        vname = retrive.getObjectRow("select className from tb_classinfo where classID = '" + classid + "'");
        classname = String.valueOf(vname.get(0));
        vname = retrive.getObjectRow("select gradeName from tb_gradeinfo where gradeID = '" + gradeid + "'");
        gradename = String.valueOf(vname.get(0));

        jTextField1.setText(vdata.get(0).toString());
        jTextField2.setText(gradename + classname);
        jTextField3.setText(vdata.get(2).toString());
        jTextField4.setText(vdata.get(4).toString());
        jTextField5.setText(vdata.get(6).toString());
        jTextField6.setText(vdata.get(5).toString());
        jComboBox3.removeAllItems();
        jComboBox3.addItem(vdata.get(3).toString());

    }

    public void jComboBox1_itemStateChanged(ItemEvent e) {
        jComboBox2.removeAllItems();
        int Index = jComboBox1.getSelectedIndex();
        String sqlStr = null;
        sqlStr  = "select classID,className from tb_classinfo where gradeID = '" + gradeID[Index] + "'";
        RetrieveObject retrieve = new RetrieveObject();
        java.util.Collection collection = null;
        java.util.Iterator iterator = null;
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        classID = new String[collection.size()];
        int i = 0;
        while(iterator.hasNext()){
            java.util.Vector vdata = (java.util.Vector)iterator.next();
            classID[i] = String.valueOf(vdata.get(0));
            jComboBox2.addItem(vdata.get(1));
            i ++;
        }
    }

    public void jComboBox2_itemStateChanged(ItemEvent e) {
        if (jComboBox2.getSelectedIndex() < 0) return;

        String cid = classID[jComboBox2.getSelectedIndex()];
        DefaultTableModel tablemodel = null;
        String[] name = {"学生编号", "班级编号", "学生姓名", "性别", "年龄", "家庭住址", "联系电话"};
        String sqlStr = "select * from tb_studentinfo where classid = '" + cid + "'";
        util.RetrieveObject bdt = new util.RetrieveObject();
        tablemodel = bdt.getTableModel(name, sqlStr);
        jTable1.setModel(tablemodel);
        jTable1.setRowHeight(24);

    }
}


class JF_view_student_jComboBox2_itemAdapter implements ItemListener {
    private JF_view_student adaptee;
    JF_view_student_jComboBox2_itemAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox2_itemStateChanged(e);
    }
}


class JF_view_student_jComboBox1_itemAdapter implements ItemListener {
    private JF_view_student adaptee;
    JF_view_student_jComboBox1_itemAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox1_itemStateChanged(e);
    }
}


class JF_view_student_jTable1_mouseAdapter extends MouseAdapter {
    private JF_view_student adaptee;
    JF_view_student_jTable1_mouseAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jTable1_mouseClicked(e);
    }
}


class JF_view_student_jBdel_actionAdapter implements ActionListener {
    private JF_view_student adaptee;
    JF_view_student_jBdel_actionAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBdel_actionPerformed(e);
    }
}


class JF_view_student_jBrefresh_actionAdapter implements ActionListener {
    private JF_view_student adaptee;
    JF_view_student_jBrefresh_actionAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBrefresh_actionPerformed(e);
    }
}


class JF_view_student_jBexit_actionAdapter implements ActionListener {
    private JF_view_student adaptee;
    JF_view_student_jBexit_actionAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}


class JF_view_student_jBadd_actionAdapter implements ActionListener {
    private JF_view_student adaptee;
    JF_view_student_jBadd_actionAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBadd_actionPerformed(e);
    }
}


class JF_view_student_jBsave_actionAdapter implements ActionListener {
    private JF_view_student adaptee;
    JF_view_student_jBsave_actionAdapter(JF_view_student adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBsave_actionPerformed(e);
    }
}
