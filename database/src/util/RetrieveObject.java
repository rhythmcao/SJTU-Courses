package util;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.Collection;
public class RetrieveObject {
    private Connection connection = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;

    public RetrieveObject(){
    }
    public Collection getTableCollection(String sqlStr){
        System.out.println("执行的集合查询为 :" + sqlStr);
        Collection collection = new Vector();
        connection = CommonaJdbc.conection;
        try{
            rs = connection.prepareStatement(sqlStr).executeQuery();
            rsmd = rs.getMetaData();
            while(rs.next()){
                Vector vdata = new Vector();
                for ( int i = 1 ; i <= rsmd.getColumnCount() ; i ++){
                    vdata.addElement(rs.getObject(i));
                }
                collection.add(vdata);
            }
        }catch(java.sql.SQLException sql){
            new view.JF_view_error("执行的SQL语句为:\n" + sqlStr + "\n错误信息为：" + sql.getMessage());
            sql.printStackTrace();
            return null;
        }
        return collection;
    }
    public DefaultTableModel getTableModel(String[] name,String sqlStr){
        Vector vname = new Vector();
        for (int i = 0 ; i < name.length ; i++){
            vname.addElement(name[i]);
        }
        DefaultTableModel tableModel = new DefaultTableModel(vname,0);

        connection = CommonaJdbc.conection;
        try{
            rs = connection.prepareStatement(sqlStr).executeQuery();
            rsmd = rs.getMetaData();
            while(rs.next()){
                Vector vdata = new Vector();
                for ( int i = 1 ; i <= rsmd.getColumnCount() ; i ++){
                    vdata.addElement(rs.getObject(i));
                }
                tableModel.addRow(vdata);
            }
        }catch(java.sql.SQLException sql){
            sql.printStackTrace();
            new view.JF_view_error("执行的SQL语句为:\n" + sqlStr + "\n错误信息为：" +sql.getMessage());
            return null;
        }
        return tableModel;
    }
    //获得满足条件的数据
    public Vector getObjectRow(String sqlStr){
        Vector vdata = new Vector();
        connection = CommonaJdbc.conection;
        try{
            rs = connection.prepareStatement(sqlStr).executeQuery();
            rsmd = rs.getMetaData();
            while(rs.next()){
                for ( int i = 1 ; i <= rsmd.getColumnCount() ; i ++){
                    vdata.addElement(rs.getObject(i));
                }
            }
        }catch(java.sql.SQLException sql){
            sql.printStackTrace();
            return null;
        }
        return vdata;
    }
}
