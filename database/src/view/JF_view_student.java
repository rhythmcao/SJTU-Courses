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
	FlowLayout flowLayout1 = new FlowLayout();
	GridLayout gridLayout1 = new GridLayout();
	JSplitPane jSplitPane1 = new JSplitPane();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JScrollPane jScrollPane1 = new JScrollPane();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel jLabel9 = new JLabel();
	JLabel jLabel10 = new JLabel();
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JComboBox jComboBox3 = new JComboBox();
	JComboBox jComboBox4 = new JComboBox();
	JButton jBadd = new JButton();
	JButton jBsave = new JButton();
	JButton jBrefresh = new JButton();
	JButton jBexit = new JButton();
	JButton jBdel = new JButton();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JTextField jTextField3 = new JTextField();
	JTextField jTextField4 = new JTextField();
	JTextField jTextField5 = new JTextField();
	JTextField jTextField6 = new JTextField();
	JTable jTable1 = new JTable();

	String gradeName[] = null;
	String className[] = null;
	String classID[] = null;

	JF_view_student_jComboBox1_itemAdapter itemlistener1 = new JF_view_student_jComboBox1_itemAdapter(this);
	JF_view_student_jComboBox2_itemAdapter itemlistener2 = new JF_view_student_jComboBox2_itemAdapter(this);

	public JF_view_student() {
		try {
			jbInit();
			initJComboBox();
			buildTable();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setClosable(true);
		getContentPane().setLayout(borderLayout1);
		jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jSplitPane1.setOpaque(false);
		jSplitPane1.setVerifyInputWhenFocusTarget(false);
		jPanel1.setLayout(flowLayout1);
		jPanel2.setLayout(gridLayout1);
		flowLayout1.setAlignment(FlowLayout.RIGHT);
		gridLayout1.setColumns(4);
		gridLayout1.setRows(4);

		jLabel1.setText("所属年级:");
		jLabel2.setText("所属班级:");
		jBadd.setText("添加");
		jBadd.addActionListener(new JF_view_student_jBadd_actionAdapter(this));
		jBsave.setText("存盘");
		jBsave.setMnemonic('S');
		jBsave.addActionListener(new JF_view_student_jBsave_actionAdapter(this));
		jBrefresh.setText("刷新");
		jBrefresh.addActionListener(new JF_view_student_jBrefresh_actionAdapter(this));
		jBexit.setText("退出");
		jBexit.addActionListener(new JF_view_student_jBexit_actionAdapter(this));
		jBdel.setText("删除");
		jBdel.addActionListener(new JF_view_student_jBdel_actionAdapter(this));
		jLabel3.setText("学生编号");
		jLabel3.setHorizontalAlignment(JLabel.CENTER);
		jTextField1.setText("");
		jLabel4.setText("班级编号");
		jLabel4.setHorizontalAlignment(JLabel.CENTER);
		jComboBox3.setEditable(true);
		jTextField2.setText("");
		jLabel5.setText("学生姓名");
		jLabel5.setHorizontalAlignment(JLabel.CENTER);
		jTextField3.setText("");
		jLabel6.setText("性别");
		jLabel6.setHorizontalAlignment(JLabel.CENTER);
		jComboBox4.setEditable(true);
		jLabel7.setText("年龄");
		jLabel7.setHorizontalAlignment(JLabel.CENTER);
		jTextField4.setText("");
		jLabel8.setText("家庭地址");
		jLabel8.setHorizontalAlignment(JLabel.CENTER);
		jTextField5.setText("");
		jLabel9.setText("联系电话");
		jLabel9.setHorizontalAlignment(JLabel.CENTER);
		jLabel10.setText("电子邮件");
		jLabel10.setHorizontalAlignment(JLabel.CENTER);
		jTextField6.setText("");
		jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jTable1.addMouseListener(new JF_view_student_jTable1_mouseAdapter(this));
		jPanel1.add(jLabel1);
		jPanel1.add(jComboBox1);
		jPanel1.add(jLabel2);
		jPanel1.add(jComboBox2);
		jPanel1.add(jBrefresh);
		jPanel1.add(jBadd);
		jPanel1.add(jBdel);
		jPanel1.add(jBsave);
		jPanel1.add(jBexit);
		jSplitPane1.add(jPanel2, JSplitPane.BOTTOM);
		jPanel2.add(jLabel3);
		jPanel2.add(jTextField1);
		jPanel2.add(jLabel4);
		jPanel2.add(jComboBox3);
		jPanel2.add(jLabel5);
		jPanel2.add(jTextField2);
		jPanel2.add(jLabel6);
		jPanel2.add(jComboBox4);
		jPanel2.add(jLabel7);
		jPanel2.add(jTextField3);
		jPanel2.add(jLabel8);
		jPanel2.add(jTextField4);
		jPanel2.add(jLabel9);
		jPanel2.add(jTextField5);
		jPanel2.add(jLabel10);
		jPanel2.add(jTextField6);
		jTextField1.addKeyListener(new SendFocuseAdapter(jTextField2));
		jTextField2.addKeyListener(new SendFocuseAdapter(jTextField3));
		jTextField3.addKeyListener(new SendFocuseAdapter(jTextField4));
		jTextField4.addKeyListener(new SendFocuseAdapter(jTextField5));
		jTextField5.addKeyListener(new SendFocuseAdapter(jTextField6));

		jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
		this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jTable1);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
		setSize(640, 440);
		setVisible(true);
		jSplitPane1.setDividerLocation(220);
	}

	public void initJComboBox() {
		jComboBox1.removeAllItems();
		String sqlStr = null;
		sqlStr = "select gradeName from tb_gradeinfo";
		RetrieveObject retrieve = new RetrieveObject();
		java.util.Collection collection = null;
		java.util.Iterator iterator = null;
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		if (collection.size() != 0) {
			gradeName = new String[collection.size()];
			int i = 0;
			while (iterator.hasNext()) {
				java.util.Vector vdata = (java.util.Vector) iterator.next();
				gradeName[i] = String.valueOf(vdata.get(0));
				jComboBox1.addItem(gradeName[i]);
				i++;
			}
		} else {
			gradeName = null;
		}

		jComboBox2.removeAllItems();
		String grade = jComboBox1.getSelectedItem().toString();
		sqlStr = "select c.className from tb_classinfo c inner join tb_gradeinfo g on c.gradeID=g.gradeID where g.gradeName='"
				+ grade + "'";
		retrieve = new RetrieveObject();
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		if (collection.size() != 0) {
			className = new String[collection.size()];
			int i = 0;
			while (iterator.hasNext()) {
				java.util.Vector vdata = (java.util.Vector) iterator.next();
				className[i] = String.valueOf(vdata.get(0));
				jComboBox2.addItem(className[i]);
				i++;
			}
		} else {
			className = null;
		}

		jComboBox3.removeAllItems();
		sqlStr = "select classID from tb_classinfo";
		retrieve = new RetrieveObject();
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		if (collection.size() != 0) {
			classID = new String[collection.size()];
			int i = 0;
			while (iterator.hasNext()) {
				java.util.Vector vdata = (java.util.Vector) iterator.next();
				classID[i] = String.valueOf(vdata.get(0));
				jComboBox3.addItem(classID[i]);
				i++;
			}
		} else {
			classID = null;
		}

		jComboBox4.removeAllItems();
		jComboBox4.addItem("男");
		jComboBox4.addItem("女");
		jComboBox1.addItemListener(itemlistener1);
		jComboBox2.addItemListener(itemlistener2);
	}

	public void buildTable() {
		if (className == null)
			return;
		DefaultTableModel tablemodel = null;
		String[] name = { "学生编号", "班级编号", "学生姓名", "性别", "年龄", "家庭住址", "联系电话", "电子邮件" };
		String sqlStr = "select s.* from tb_studentinfo s inner join tb_gradeinfo g inner join tb_classinfo c on s.classID=c.classID and c.gradeID=g.gradeID where c.className='"
				+ jComboBox2.getSelectedItem().toString() + "'";
		util.RetrieveObject bdt = new util.RetrieveObject();
		tablemodel = bdt.getTableModel(name, sqlStr);
		jTable1.setModel(tablemodel);
		jTable1.setRowHeight(24);
	}

	public void jBadd_actionPerformed(ActionEvent e) {
		if (className == null) {
			JOptionPane.showMessageDialog(null, "班级名称为空,请重新选择班级", "系统提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String sqlStr = "select c.classID from tb_classinfo c inner join tb_gradeinfo g on c.gradeID=g.gradeID where g.gradeName='"
				+ jComboBox1.getSelectedItem().toString() + "' and c.className='"
				+ jComboBox2.getSelectedItem().toString() + "'";
		java.util.Vector vdata = null;
		RetrieveObject retrive = new RetrieveObject();
		vdata = retrive.getObjectRow(sqlStr);
		ProduceMaxBh pm = new util.ProduceMaxBh();
		String sqlMax = "select max(stuid) from tb_studentinfo where classID = '" + vdata.get(0).toString().trim() + "'";
		String stuid = pm.getMaxBh(sqlMax, "");
		jTextField1.setText(stuid);
		jComboBox3.setSelectedItem(vdata.get(0).toString().trim());
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField4.setText("");
		jTextField5.setText("");
		jTextField6.setText("");
		jComboBox4.setSelectedItem("男");
		jTextField2.requestFocus();
	}

	public void jBexit_actionPerformed(ActionEvent e) {
		javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "是否退出学生基本信息录入?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.YES_OPTION) {
			manger.closeFrame(this);
		}

	}

	public void jBsave_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showOptionDialog(null, "是否存盘学生基本数据信息?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.NO_OPTION)
			return;

		model.Obj_student object = new model.Obj_student();
		object.setStuid(jTextField1.getText().trim());
		object.setClassID(String.valueOf(jComboBox3.getSelectedItem()));
		object.setStuname(jTextField2.getText().trim());
		int age = 0;
		try {
			age = Integer.parseInt(jTextField3.getText().trim());
		} catch (java.lang.NumberFormatException formate) {
			JOptionPane.showMessageDialog(null, "数据录入有误，错误信息:\n" + formate.getMessage(), "系统提示",
					JOptionPane.ERROR_MESSAGE);
			jTextField3.requestFocus();
			return;
		}
		object.setAge(age);
		object.setSex(String.valueOf(jComboBox4.getSelectedItem()));
		object.setAddress(jTextField4.getText().trim());
		object.setPhone(jTextField5.getText().trim());
		object.setEmail(jTextField6.getText().trim());

		util.JdbcAdapter adapter = new util.JdbcAdapter();
		if (adapter.InsertOrUpdateObject(object)) {
			String sqlStr = "select g.gradeName,c.className from tb_gradeinfo g inner join tb_classinfo c on g.gradeID=c.gradeID where classID='"
					+ String.valueOf(jComboBox3.getSelectedItem()) + "'";
			RetrieveObject retrieve = new RetrieveObject();
			java.util.Vector vdata = retrieve.getObjectRow(sqlStr);
			jComboBox1.setSelectedItem(vdata.get(0).toString().trim());
			jComboBox2.setSelectedItem(vdata.get(1).toString().trim());
			ActionEvent event = new ActionEvent(jBrefresh, 0, null);
			jBrefresh_actionPerformed(event);
		}
	}

	public void jBrefresh_actionPerformed(ActionEvent e) {
		buildTable();
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField4.setText("");
		jTextField5.setText("");
		jTextField6.setText("");
		jComboBox4.setSelectedItem("男");
	}

	public void jBdel_actionPerformed(ActionEvent e) {
		if (jTextField1.getText().trim().length() <= 0)
			return;

		int result = JOptionPane.showOptionDialog(null, "是否删除学生的基本信息数据?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.NO_OPTION)
			return;

		String sqlDel = "delete from tb_studentinfo where stuid = '" + jTextField1.getText().trim() + "'";
		JdbcAdapter jdbcAdapter = new JdbcAdapter();
		if (jdbcAdapter.DeleteObject(sqlDel)) {
			ActionEvent event = new ActionEvent(jBrefresh, 0, null);
			jBrefresh_actionPerformed(event);
		}
	}

	public void jTable1_mouseClicked(MouseEvent e) {
		String id = null;
		String sqlStr = null;
		int selectrow = 0;
		selectrow = jTable1.getSelectedRow();
		if (selectrow < 0)
			return;

		id = jTable1.getValueAt(selectrow, 0).toString();
		sqlStr = "select * from tb_studentinfo where stuid = '" + id + "'";
		java.util.Vector vdata = null;
		RetrieveObject retrive = new RetrieveObject();
		vdata = retrive.getObjectRow(sqlStr);

		jTextField1.setText(vdata.get(0).toString());
		jTextField2.setText(vdata.get(2).toString());
		jTextField3.setText(vdata.get(4).toString());
		jTextField4.setText(vdata.get(5).toString());
		jTextField5.setText(vdata.get(6).toString());
		jTextField6.setText(vdata.get(7).toString());
		jComboBox3.setSelectedItem(vdata.get(1).toString().trim());
		jComboBox4.setSelectedItem(vdata.get(3).toString().trim());
	}

	public void jComboBox1_itemStateChanged(ItemEvent e) {
		jComboBox2.removeItemListener(itemlistener2);
		jComboBox2.removeAllItems();
		String grade = jComboBox1.getSelectedItem().toString();
		String sqlStr = "select c.className from tb_classinfo c inner join tb_gradeinfo g on c.gradeID=g.gradeID where g.gradeName='"
				+ grade + "'";
		RetrieveObject retrieve = new RetrieveObject();
		java.util.Collection collection = retrieve.getTableCollection(sqlStr);
		java.util.Iterator iterator = collection.iterator();
		if (collection.size() != 0) {
			className = new String[collection.size()];
			int i = 0;
			while (iterator.hasNext()) {
				java.util.Vector vdata = (java.util.Vector) iterator.next();
				className[i] = String.valueOf(vdata.get(0));
				jComboBox2.addItem(className[i]);
				i++;
			}
			buildTable();
			jComboBox2.addItemListener(itemlistener2);
		} else {
			className = null;
		}
	}

	public void jComboBox2_itemStateChanged(ItemEvent e) {
		buildTable();
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
