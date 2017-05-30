package data;
import java.sql.*;

public class ConnectToMySQL {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_student?characterEncoding=utf-8&useSSL=false";
    
    static final String USER = "root";
    static final String PASS = "123456";
    
    // 连接测试主程序
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt=null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt=conn.createStatement();
            // 创建班级，一个年级十个班
//            new GenerateClasses(conn,stmt).AutoGenerateClasses();
            // 为每一个班分配一个教师
//            new GenerateTeachers(conn, stmt).AutoGenerateTeachers();
            // 为每一个班级创建一些学生
            new GenerateStudents(conn,stmt).AutoGenerateStudents();
            // 为每一个学生每一门课创建成绩
//            new GenerateScores(conn,stmt).AutoGenerateScores();
            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
