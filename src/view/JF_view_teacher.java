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

public class JF_view_teacher extends JInternalFrame {
	BorderLayout borderLayout1 = new BorderLayout();
	FlowLayout flowLayout1 = new FlowLayout();
	GridLayout gridLayout1 = new GridLayout();
	JSplitPane jSplitPane1 = new JSplitPane();
	JScrollPane jScrollPane1 = new JScrollPane();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JButton jBrefresh = new JButton();
	JButton jBdel = new JButton();
	JButton jBadd = new JButton();
	JButton jBsave = new JButton();
	JButton jBexit = new JButton();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JTable jTable1 = new JTable();
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JComboBox jComboBox3 = new JComboBox();
	JComboBox jComboBox4 = new JComboBox();

	String classid[] = null;
	String knowledge[] = null;
	String knowlevel[] = null;

	public JF_view_teacher() {
		try {
			jbInit();
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
		flowLayout1.setAlignment(FlowLayout.RIGHT);
		jPanel2.setLayout(gridLayout1);
		gridLayout1.setColumns(4);
		gridLayout1.setRows(3);
		jBdel.setText("删除");
		jBdel.addActionListener(new JF_view_teacher_jBdel_actionAdapter(this));
		jBadd.setText("添加");
		jBadd.addActionListener(new JF_view_teacher_jBadd_actionAdapter(this));
		jBsave.setText("存盘");
		jBsave.setMnemonic('S'); // 快捷键设置为Alt+S
		jBsave.addActionListener(new JF_view_teacher_jBsave_actionAdapter(this));
		jBrefresh.setText("刷新");
		jBrefresh.addActionListener(new JF_view_teacher_jBrefresh_actionAdapter(this));
		jBexit.setText("退出");
		jBexit.addActionListener(new JF_view_teacher_jBexit_actionAdapter(this));
		jLabel1.setText("教师编号");
		jLabel2.setText("班级编号");
		jLabel3.setText("教师姓名");
		jLabel4.setText("性别");
		jLabel5.setText("教学水平");
		jLabel6.setText("教师职称");
		jLabel1.setHorizontalAlignment(JLabel.CENTER);
		jLabel2.setHorizontalAlignment(JLabel.CENTER);
		jLabel3.setHorizontalAlignment(JLabel.CENTER);
		jLabel4.setHorizontalAlignment(JLabel.CENTER);
		jLabel5.setHorizontalAlignment(JLabel.CENTER);
		jLabel6.setHorizontalAlignment(JLabel.CENTER);
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField1.addKeyListener(new SendFocuseAdapter(jTextField2));
		jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.setClosable(true);
		jTable1.addMouseListener(new JF_view_teacher_jTable1_mouseAdapter(this));
		jComboBox1.setEditable(false);
		jComboBox2.setEditable(true);
		jComboBox3.setEditable(true);
		jComboBox4.setEditable(true);
		jPanel1.add(jBrefresh);
		jPanel1.add(jBadd);
		jPanel1.add(jBdel);
		jPanel1.add(jBsave);
		jPanel1.add(jBexit);
		jSplitPane1.add(jPanel2, JSplitPane.BOTTOM);
		jPanel2.add(jLabel1);
		jPanel2.add(jTextField1);
		jPanel2.add(jLabel2);
		jPanel2.add(jComboBox1);
		jPanel2.add(jLabel3);
		jPanel2.add(jTextField2);
		jPanel2.add(jLabel4);
		jPanel2.add(jComboBox3);
		jPanel2.add(jLabel6);
		jPanel2.add(jComboBox2);
		jPanel2.add(jLabel5);
		jPanel2.add(jComboBox4);
		jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
		this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jTable1);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
		setSize(640, 440);
		setVisible(true);
		jSplitPane1.setDividerLocation(256);
		initJcombox();
		ActionEvent event = new ActionEvent(jBrefresh, 0, null);
		jBrefresh_actionPerformed(event);
	}

	private void initJcombox() {
		String sqlStr = null;
		int i = 0;
		jComboBox1.removeAllItems();
		sqlStr = "select classID from tb_classinfo";
		RetrieveObject retrieve = new RetrieveObject();
		java.util.Collection collection = null;
		java.util.Iterator iterator = null;
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		if (collection.size() != 0) {
			classid = new String[collection.size()];
			while (iterator.hasNext()) {
				java.util.Vector vdata = (java.util.Vector) iterator.next();
				classid[i] = String.valueOf(vdata.get(0));
				jComboBox1.addItem(classid[i]);
				i++;
			}
		} else
			classid = null;

		jComboBox2.removeAllItems();
		sqlStr = "select knowledge from tb_teacher";
		retrieve = new RetrieveObject();
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		if (collection.size() != 0) {
			knowledge = new String[collection.size()];
			i = 0;
			while (iterator.hasNext()) {
				java.util.Vector vdata = (java.util.Vector) iterator.next();
				knowledge[i] = String.valueOf(vdata.get(0));
				jComboBox2.addItem(knowledge[i]);
				i++;
			}
		} else
			knowledge = null;

		jComboBox3.removeAllItems();
		jComboBox3.addItem("男");
		jComboBox3.addItem("女");

		jComboBox4.removeAllItems();
		sqlStr = "select knowlevel from tb_teacher";
		retrieve = new RetrieveObject();
		collection = null;
		iterator = null;
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		if (collection.size() != 0) {
			knowlevel = new String[collection.size()];
			i = 0;
			while (iterator.hasNext()) {
				java.util.Vector vdata = (java.util.Vector) iterator.next();
				knowlevel[i] = String.valueOf(vdata.get(0));
				jComboBox4.addItem(knowlevel[i]);
				i++;
			}
		} else
			knowlevel = null;

	}

	// 添加一条数据
	public void jBadd_actionPerformed(ActionEvent e) {
		if (classid != null)
			if (classid[0] != null)
				jComboBox1.setSelectedItem(classid[0]);
		if (knowledge != null)
			if (knowledge[0] != null)
				jComboBox2.setSelectedItem(knowledge[0]);
		if (knowlevel != null)
			if (knowlevel[0] != null)
				jComboBox4.setSelectedItem(knowlevel[0]);
		jComboBox3.setSelectedItem("男");
		String sqlMax = "select max(teaid) from tb_teacher ";
		ProduceMaxBh pm = new util.ProduceMaxBh();
		String teaid = pm.getMaxBh(sqlMax, "");
		jTextField1.setText(teaid);
		jTextField2.setText("");
		jTextField2.requestFocus();
	}

	public void jBexit_actionPerformed(ActionEvent e) {
		javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "是否退出教师基本信息管理?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.YES_OPTION) {
			manger.closeFrame(this);
		}

	}

	public void jBsave_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showOptionDialog(null, "是否存盘教师基本数据信息?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.NO_OPTION)
			return;

		model.Obj_teacher object = new model.Obj_teacher();
		object.setTeaid(jTextField1.getText().trim());
		object.setClassID(classid[jComboBox1.getSelectedIndex()]);
		object.setTeaname(jTextField2.getText().trim());
		object.setSex(String.valueOf(jComboBox3.getSelectedItem()));
		object.setKnowlevel(String.valueOf(jComboBox4.getSelectedItem()));
		object.setKnowledge(String.valueOf(jComboBox2.getSelectedItem()));

		util.JdbcAdapter adapter = new util.JdbcAdapter();
		if (adapter.InsertOrUpdateObject(object)) {
			ActionEvent event = new ActionEvent(jBrefresh, 0, null);
			jBrefresh_actionPerformed(event);
			jTextField1.setText("");
			jTextField2.setText("");
		}
	}

	public void jBrefresh_actionPerformed(ActionEvent e) {
		DefaultTableModel tablemodel = null;
		String[] name = { "教师编号", "班级编号", "教师姓名", "性别", "教师职称", "教学水平" };
		String sqlStr = "select * from tb_teacher";
		util.RetrieveObject bdt = new util.RetrieveObject();
		tablemodel = bdt.getTableModel(name, sqlStr);
		jTable1.setModel(tablemodel);
		jTable1.setRowHeight(24);
		initJcombox();

	}

	public void jBdel_actionPerformed(ActionEvent e) {
		if (jTextField1.getText().trim().length() <= 0)
			return;
		int result = JOptionPane.showOptionDialog(null, "是否删除教师的基本信息数据?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.NO_OPTION)
			return;
		String sqlDel = "delete from tb_teacher where teaid = '" + jTextField1.getText().trim() + "'";
		JdbcAdapter jdbcAdapter = new JdbcAdapter();
		if (jdbcAdapter.DeleteObject(sqlDel)) {
			jTextField1.setText("");
			jTextField2.setText("");
			ActionEvent event = new ActionEvent(jBrefresh, 0, null);
			jBrefresh_actionPerformed(event);

		}

	}

	public void jTable1_mouseClicked(MouseEvent e) {
		String teaid = null;
		String sqlStr = null;
		int selectrow = 0;
		selectrow = jTable1.getSelectedRow();
		if (selectrow < 0)
			return;

		teaid = jTable1.getValueAt(selectrow, 0).toString();
		sqlStr = "SELECT teaid,classID,teaname, sex,knowledge,knowlevel FROM tb_teacher where teaid = '" + teaid + "'";
		java.util.Vector vdata = null;
		RetrieveObject retrive = new RetrieveObject();
		vdata = retrive.getObjectRow(sqlStr);

		jTextField1.setText(vdata.get(0).toString());
		jTextField2.setText(vdata.get(2).toString());
		jComboBox1.setSelectedItem(vdata.get(1).toString());
		jComboBox2.setSelectedItem(vdata.get(4).toString());
		jComboBox3.setSelectedItem(vdata.get(3).toString());
		jComboBox4.setSelectedItem(vdata.get(5).toString());
	}

}

class JF_view_teacher_jTable1_mouseAdapter extends MouseAdapter {
	private JF_view_teacher adaptee;

	JF_view_teacher_jTable1_mouseAdapter(JF_view_teacher adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.jTable1_mouseClicked(e);
	}
}

class JF_view_teacher_jBdel_actionAdapter implements ActionListener {
	private JF_view_teacher adaptee;

	JF_view_teacher_jBdel_actionAdapter(JF_view_teacher adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBdel_actionPerformed(e);
	}
}

class JF_view_teacher_jBrefresh_actionAdapter implements ActionListener {
	private JF_view_teacher adaptee;

	JF_view_teacher_jBrefresh_actionAdapter(JF_view_teacher adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBrefresh_actionPerformed(e);
	}
}

class JF_view_teacher_jBexit_actionAdapter implements ActionListener {
	private JF_view_teacher adaptee;

	JF_view_teacher_jBexit_actionAdapter(JF_view_teacher adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBexit_actionPerformed(e);
	}
}

class JF_view_teacher_jBadd_actionAdapter implements ActionListener {
	private JF_view_teacher adaptee;

	JF_view_teacher_jBadd_actionAdapter(JF_view_teacher adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBadd_actionPerformed(e);
	}
}

class JF_view_teacher_jBsave_actionAdapter implements ActionListener {
	private JF_view_teacher adaptee;

	JF_view_teacher_jBsave_actionAdapter(JF_view_teacher adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBsave_actionPerformed(e);
	}
}
