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
        Statement stmt = null;
        try{
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            // ִ�в�ѯ
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT actor_id,first_name,last_name,last_update FROM actor";
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("actor_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                Timestamp date = rs.getTimestamp("last_update");
    
                // �������
                System.out.print("Actor id: " + id+"\t");
                System.out.print("First_name: " + fname+"\t");
                System.out.print("Last_name: " + lname+"\t");
                System.out.print("Last_update: "+date+"\n");
            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
