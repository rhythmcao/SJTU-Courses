package data;

import java.sql.*;

public class GenerateScores {
	Connection conn = null;
	Statement stmt = null;
	String[] kindID = null;
	String[] code=null;
	public GenerateScores(Connection conn, Statement stmt) {
		super();
		this.conn = conn;
		this.stmt = stmt;
	}

	public static String getScore(int start, int end) {
		return String.valueOf((float) (Math.random() * (end - start + 1) + start));
	}

	public String getDate(String kindID) {
		if (kindID.equals("01"))
			return "2017-03-15";
		else if (kindID.equals("期中考试"))
			return "2017-04-15";
		else if (kindID.equals("第一次月考"))
			return "2017-05-15";
		else
			return "2017-06-15";
	}

	public void AutoGenerateScores() throws SQLException {
		String stusql = null, kindsql = null, codesql = null;
		String stuid = null, stuname = null;
		String sql = null;
		
		Statement kindstmt = conn.createStatement();
		kindsql = "select count(distinct kindID) from tb_examkinds";
		ResultSet kind_rs = kindstmt.executeQuery(kindsql);
		int kinds=0;
		while(kind_rs.next())
			kinds=kind_rs.getInt(1);
		kindID=new String[kinds];
		kindsql="select distinct kindID from tb_examkinds";
		kind_rs=kindstmt.executeQuery(kindsql);
		int i=0;
		while(kind_rs.next()){
			kindID[i]=kind_rs.getString(1);
			i+=1;
		}
		kindstmt.close();
		
		Statement codestmt = conn.createStatement();
		codesql = "select count(distinct code) from tb_subject";
		ResultSet code_rs = codestmt.executeQuery(codesql);
		int codes=0;
		while(code_rs.next())
			codes=code_rs.getInt(1);
		code=new String[codes];
		codesql="select distinct code from tb_subject";
		code_rs=codestmt.executeQuery(codesql);
		i=0;
		while(code_rs.next()){
			code[i]=code_rs.getString(1);
			i+=1;
		}
		codestmt.close();
		
		Statement stustmt = conn.createStatement();
		stusql = "select stuid,stuname from tb_studentinfo";
		ResultSet stu_rs = stustmt.executeQuery(stusql);
		int j = 0;
		while (stu_rs.next()) {
			for (i = 0; i < kindID.length; ++i)
				for (j = 0; j < code.length; ++j) {
					stuid = stu_rs.getString(1);
					stuname = stu_rs.getString(2);
					sql = "insert into tb_gradeinfo_sub(stuid,stuname,kindID,code,grade,examdate) values('" 
					+ stuid + "','" 
					+ stuname + "','" 
					+ kindID[i] + "','" 
					+ code[j] + "'," 
					+ getScore(50, 100) + ",'"
					+ getDate(kindID[i]) + "')";
					stmt.executeUpdate(sql);
				}
		}
		stustmt.close();
	}
}
