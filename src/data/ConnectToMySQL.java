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
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT actor_id,first_name,last_name,last_update FROM actor";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("actor_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                Timestamp date = rs.getTimestamp("last_update");
    
                // 输出数据
                System.out.print("Actor id: " + id+"\t");
                System.out.print("First_name: " + fname+"\t");
                System.out.print("Last_name: " + lname+"\t");
                System.out.print("Last_update: "+date+"\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
