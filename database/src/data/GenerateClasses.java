package data;

import java.sql.*;

public class GenerateClasses {
	public static final String[] grade = { "01", "02", "03", "04", "05", "06" };
	public static final String[] gradeName={"初一","初二","初三","高一","高二","高三"};
	Connection conn = null;
	Statement stmt=null;

	public GenerateClasses(Connection conn,Statement stmt) {
		super();
		this.conn = conn;
		this.stmt=stmt;
	}

	public void AutoGenerateClasses() throws SQLException {
		String classid=null;
		String classname=null;
		String sql=null;
		int i = 0,j=1;
		for (i = 0; i < grade.length; ++i) {
			for(j=1;j<21;++j){
				if(j<10)
					classid=grade[i]+"000"+String.valueOf(j);
				else
					classid=grade[i]+"00"+String.valueOf(j);
				classname=gradeName[i]+String.valueOf(j)+"班";
				sql="insert into tb_classinfo(classID,gradeID,className) values ('"+classid+"','"+grade[i]+"','"+classname+"')";
				stmt.executeUpdate(sql);
			}
			
		}

	}
}
