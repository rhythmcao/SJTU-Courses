package view;

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

public class JF_view_sysset_grade extends JInternalFrame {
	public JF_view_sysset_grade() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		buildTable();
		this.getContentPane().setLayout(borderLayout1);
		this.setClosable(true);
		setSize(380, 300);
		setVisible(true);
		gridLayout1.setColumns(2);
		gridLayout1.setRows(2);
		jLabel1.setText("年级编号");
		jLabel1.setHorizontalAlignment(JLabel.CENTER);
		jTextField1.setText("");
		jLabel2.setText("年级名称");
		jLabel2.setHorizontalAlignment(JLabel.CENTER);
		jTextField2.setText("");

		jBdel.setText("删除");
		jBdel.addActionListener(new JF_view_sysset_grade_jBdel_actionAdapter(this));
		jBadd.setText("添加");
		jBadd.addActionListener(new JF_view_sysset_grade_jBadd_actionAdapter(this));
		jBsave.setText("存盘");
		jBsave.addActionListener(new JF_view_sysset_grade_jButton3_actionAdapter(this));
		jBexit.setText("退出");
		jBexit.addActionListener(new JF_view_sysset_grade_jBexit_actionAdapter(this));

		jPanel2.setLayout(flowLayout1);
		flowLayout1.setAlignment(FlowLayout.RIGHT);
		jTable1.addMouseListener(new JF_view_sysset_grade_jTable1_mouseAdapter(this));
		this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
		jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
		jScrollPane1.getViewport().add(jTable1);
		jSplitPane1.add(jPanel1, JSplitPane.BOTTOM);
		jPanel1.setLayout(gridLayout1);
		jPanel1.add(jLabel1);
		jPanel1.add(jTextField1);
		jPanel1.add(jLabel2);
		jPanel1.add(jTextField2);
		this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel2.add(jBdel);
		jPanel2.add(jBadd);
		jPanel2.add(jBsave);
		jPanel2.add(jBexit);
		jSplitPane1.setDividerLocation(164);

		jTextField1.addKeyListener(new SendFocuseAdapter(jTextField2));
		buildTable();
	}

	FlowLayout flowLayout1 = new FlowLayout();
	BorderLayout borderLayout1 = new BorderLayout();
	GridLayout gridLayout1 = new GridLayout();
	JSplitPane jSplitPane1 = new JSplitPane();
	JScrollPane jScrollPane1 = new JScrollPane();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JTable jTable1 = new JTable();
	JLabel jLabel1 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JLabel jLabel2 = new JLabel();
	JTextField jTextField2 = new JTextField();
	JButton jBdel = new JButton();
	JButton jBadd = new JButton();
	JButton jBsave = new JButton();
	JButton jBexit = new JButton();

	public void buildTable() {
		DefaultTableModel tablemodel = null;
		String[] name = { "年级编号", "年级名称" };
		String sqlStr = "select * from tb_gradeinfo";
		util.RetrieveObject bdt = new util.RetrieveObject();
		tablemodel = bdt.getTableModel(name, sqlStr);
		jTable1.setModel(tablemodel);
		jTable1.setRowHeight(24);
	}

	public void jBsave_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showOptionDialog(null, "是否存盘年级信息数据?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.NO_OPTION)
			return;

		model.Obj_gradeinfo objgradeinfo = new model.Obj_gradeinfo();
		objgradeinfo.setGradeID(jTextField1.getText().trim());
		objgradeinfo.setGradeName(jTextField2.getText().trim());
		JdbcAdapter jdbcAdapter = new JdbcAdapter();
		if (jdbcAdapter.InsertOrUpdateObject(objgradeinfo))
			buildTable();

	}

	public void jBexit_actionPerformed(ActionEvent e) {
		javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "是否退出年级信息设置?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.YES_OPTION) {
			manger.closeFrame(this);
		}
	}

	public void jBadd_actionPerformed(ActionEvent e) {
		ProduceMaxBh pm = new util.ProduceMaxBh();
		String sqlStr = null, grdeid = null;
		sqlStr = "SELECT MAX(gradeID) FROM tb_gradeinfo";
		grdeid = pm.getMaxBh(sqlStr, "");
		jTextField1.setText(grdeid);
		jTextField2.setText("");
	}

	public void jTable1_mouseClicked(MouseEvent e) {
		String id = null;
		String sqlStr = null;
		int selectrow = 0;
		selectrow = jTable1.getSelectedRow();
		System.out.println("selectrow = " + selectrow);
		if (selectrow < 0)
			return;

		id = jTable1.getValueAt(selectrow, 0).toString();
		sqlStr = "select * from tb_gradeinfo where gradeID = '" + id + "'";
		java.util.Vector vdata = null;
		RetrieveObject retrive = new RetrieveObject();
		vdata = retrive.getObjectRow(sqlStr);

		jTextField1.setText(vdata.get(0).toString());
		jTextField2.setText(vdata.get(1).toString());
	}

	public void jBdel_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showOptionDialog(null, "是否删除年级信息数据?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.NO_OPTION)
			return;
		String sqlDel = "delete from tb_gradeinfo where gradeID = '" + jTextField1.getText().trim() + "'";
		JdbcAdapter jdbcAdapter = new JdbcAdapter();
		if (jdbcAdapter.DeleteObject(sqlDel)) {
			jTextField1.setText("");
			jTextField2.setText("");
			buildTable();
		}
	}
}

class JF_view_sysset_grade_jTable1_mouseAdapter extends MouseAdapter {
	private JF_view_sysset_grade adaptee;

	JF_view_sysset_grade_jTable1_mouseAdapter(JF_view_sysset_grade adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.jTable1_mouseClicked(e);
	}
}

class JF_view_sysset_grade_jBdel_actionAdapter implements ActionListener {
	private JF_view_sysset_grade adaptee;

	JF_view_sysset_grade_jBdel_actionAdapter(JF_view_sysset_grade adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBdel_actionPerformed(e);
	}
}

class JF_view_sysset_grade_jBadd_actionAdapter implements ActionListener {
	private JF_view_sysset_grade adaptee;

	JF_view_sysset_grade_jBadd_actionAdapter(JF_view_sysset_grade adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBadd_actionPerformed(e);
	}
}

class JF_view_sysset_grade_jBexit_actionAdapter implements ActionListener {
	private JF_view_sysset_grade adaptee;

	JF_view_sysset_grade_jBexit_actionAdapter(JF_view_sysset_grade adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBexit_actionPerformed(e);
	}
}

class JF_view_sysset_grade_jButton3_actionAdapter implements ActionListener {
	private JF_view_sysset_grade adaptee;

	JF_view_sysset_grade_jButton3_actionAdapter(JF_view_sysset_grade adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.jBsave_actionPerformed(e);
	}
}
