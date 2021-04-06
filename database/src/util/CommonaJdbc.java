package util;

import java.sql.*;

public class CommonaJdbc {
	static final String USER = "root";
	static final String PASSWORD = "123456";
	public static Connection conection = null;

	public CommonaJdbc() {
		getCon();
	}

	private Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_student?characterEncoding=utf-8&useSSL=false", USER, PASSWORD);
			System.out.println("数据库连接成功");

		} catch (java.lang.ClassNotFoundException classnotfound) {
			classnotfound.printStackTrace();
		} catch (java.sql.SQLException sql) {
			new view.JF_view_error(sql.getMessage());
			sql.printStackTrace();
		}
		return conection;
	}
}
