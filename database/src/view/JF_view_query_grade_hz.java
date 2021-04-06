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
import util.RetrieveObject;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.DefaultDesktopManager;
import java.awt.Color;

public class JF_view_query_grade_hz extends JInternalFrame {
	BorderLayout borderLayout1 = new BorderLayout();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable jTable1 = new JTable();
	JPanel jPanel1 = new JPanel();
	FlowLayout flowLayout1 = new FlowLayout();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JButton jByes = new JButton();
	JButton jBexit = new JButton();

	String gradeid[] = null;
	String gradename[] = null;
	String examkindid[] = null;
	String examkindname[] = null;

	public JF_view_query_grade_hz() {
		try {
			jbInit();
			this.initialize();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		this.setClosable(true);
		jByes.addActionListener(new JF_view_query_grade_hz_jByes_actionAdapter(this));
		jBexit.addActionListener(new JF_view_query_grade_hz_jBexit_actionAdapter(this));
		flowLayout1.setAlignment(FlowLayout.RIGHT);
		jLabel1.setForeground(Color.red);
		this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jTable1);
		jPanel1.setLayout(flowLayout1);
		jLabel2.setToolTipText("");
		jLabel2.setText("考试类别");
		jLabel3.setText("所属年级");
		jByes.setText("确定");
		jBexit.setText("退出");
		jPanel1.add(jLabel2);
		jPanel1.add(jComboBox1);
		jPanel1.add(jLabel3);
		jPanel1.add(jComboBox2);
		jPanel1.add(jByes);
		jPanel1.add(jBexit);
		this.getContentPane().add(jLabel1, java.awt.BorderLayout.SOUTH);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
		this.setSize(680, 460);
		this.setVisible(true);
	}

	public void initialize() {
		// 初始化考试类型和年级
		RetrieveObject retrieve = new RetrieveObject();
		java.util.Vector vdata = new java.util.Vector();
		String sqlStr = null;
		java.util.Collection collection = null;
		java.util.Iterator iterator = null;
		sqlStr = "SELECT * FROM tb_examkinds";
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		examkindid = new String[collection.size()];
		examkindname = new String[collection.size()];
		int i = 0;
		while (iterator.hasNext()) {
			vdata = (java.util.Vector) iterator.next();
			examkindid[i] = String.valueOf(vdata.get(0));
			examkindname[i] = String.valueOf(vdata.get(1));
			jComboBox1.addItem(examkindname[i]);
			i++;
		}
		sqlStr = "select * from tb_gradeinfo";
		collection = retrieve.getTableCollection(sqlStr);
		iterator = collection.iterator();
		gradeid = new String[collection.size()];
		gradename = new String[collection.size()];
		i = 0;
		while (iterator.hasNext()) {
			vdata = (java.util.Vector) iterator.next();
			gradeid[i] = String.valueOf(vdata.get(0));
			gradename[i] = String.valueOf(vdata.get(1));
			jComboBox2.addItem(vdata.get(1));
			i++;
		}

	}

	public void jByes_actionPerformed(ActionEvent e) {
		String sqlSubject = null;
		java.util.Collection collection = null;

		Object[] object = null;
		java.util.Iterator iterator = null;
		sqlSubject = "SELECT * FROM tb_subject";
		RetrieveObject retrieve = new RetrieveObject();
		collection = retrieve.getTableCollection(sqlSubject);
		object = collection.toArray();
		String strCode[] = new String[object.length];
		String strSubject[] = new String[object.length];

		String sqlStr = "CREATE VIEW TempView As SELECT stuid, tb_classinfo.className, ";
		for (int i = 0; i < object.length; i++) {
			String code = null, subject = null;
			java.util.Vector vdata = null;
			vdata = (java.util.Vector) object[i];
			code = String.valueOf(vdata.get(0));
			subject = String.valueOf(vdata.get(1));
			strSubject[i] = subject;

			if ((i + 1) == object.length) {
				sqlStr = sqlStr + " sum(CASE code WHEN '" + code + "' THEN grade ELSE 0 END) AS '" + subject + "'";
			} else {
				sqlStr = sqlStr + " sum(CASE code WHEN '" + code + "' THEN grade ELSE 0 END) AS '" + subject + "',";
			}
		}
		String whereStr = null;
		whereStr = " where kindID = '" + this.examkindid[jComboBox1.getSelectedIndex()]
				+ "' and subString(stuid,1,2) = '" + this.gradeid[jComboBox2.getSelectedIndex()]
				+ "' and tb_classinfo.classID = substring(tb_gradeinfo_sub.stuid, 1, 6)";

		sqlStr = sqlStr + " FROM tb_gradeinfo_sub, tb_classinfo " + whereStr
				+ " GROUP BY stuid,tb_classinfo.className ";

		util.JdbcAdapter jdbcAdapter = new util.JdbcAdapter();
		if (jdbcAdapter.BuildeDeleteTempView(sqlStr)) {
			String[] tbname = new String[object.length + 2];
			tbname[0] = "班级名称";
			tbname[1] = "学生人数";

			String sqlView = null;
			sqlView = "SELECT className AS 班级名称,COUNT(stuid) AS 学生人数,";
			for (int i = 0; i < strSubject.length; i++) {
				tbname[i + 2] = strSubject[i];
				if ((i + 1) == strSubject.length) {
					sqlView = sqlView + "avg(" + strSubject[i] + ") as '" + strSubject[i] + "'";
				} else {
					sqlView = sqlView + "avg(" + strSubject[i] + ") as '" + strSubject[i] + "',";
				}
			}
			sqlView = sqlView + " from TempView GROUP BY SUBSTRING(stuid, 1, 6), className";
			DefaultTableModel tablemodel = null;

			util.RetrieveObject bdt = new util.RetrieveObject();
			tablemodel = bdt.getTableModel(tbname, sqlView);

			jTable1.setModel(tablemodel);
			String dropSql = "DROP VIEW TempView ";
			jdbcAdapter.BuildeDeleteTempView(dropSql);
		}

		if (jTable1.getRowCount() <= 0) {
			JOptionPane.showMessageDialog(null, "没有找到满足条件的数据!!!", "系统提示", JOptionPane.INFORMATION_MESSAGE);
		}
		jTable1.setRowHeight(24);
		jLabel1.setText("共有数据【" + String.valueOf(jTable1.getRowCount()) + "】条");

	}

	public void jBexit_actionPerformed(ActionEvent e) {
		javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "是否退出学生成绩明细信息查询?", "系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
		if (result == JOptionPane.YES_OPTION) {

			manger.closeFrame(this);
		}

	}

}

class JF_view_query_grade_hz_jBexit_actionAdapter implements ActionListener {
	private JF_view_query_grade_hz adaptee;

	JF_view_query_grade_hz_jBexit_actionAdapter(JF_view_query_grade_hz adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBexit_actionPerformed(e);
	}
}

class JF_view_query_grade_hz_jByes_actionAdapter implements ActionListener {
	private JF_view_query_grade_hz adaptee;

	JF_view_query_grade_hz_jByes_actionAdapter(JF_view_query_grade_hz adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jByes_actionPerformed(e);
	}
}
