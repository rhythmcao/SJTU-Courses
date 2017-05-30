package data;
import java.sql.*;

public class ConnectToMySQL {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_student?characterEncoding=utf-8&useSSL=false";
    
    static final String USER = "root";
    static final String PASS = "123456";
    
    // ���Ӳ���������
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt=null;
        try{
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt=conn.createStatement();
            // �����༶��һ���꼶ʮ����
//            new GenerateClasses(conn,stmt).AutoGenerateClasses();
            // Ϊÿһ�������һ����ʦ
//            new GenerateTeachers(conn, stmt).AutoGenerateTeachers();
            // Ϊÿһ���༶����һЩѧ��
            new GenerateStudents(conn,stmt).AutoGenerateStudents();
            // Ϊÿһ��ѧ��ÿһ�ſδ����ɼ�
//            new GenerateScores(conn,stmt).AutoGenerateScores();
            // ��ɺ�ر�
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
