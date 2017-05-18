package util;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.util.Vector;
public class ProduceMaxBh {

    public String getMaxBh(String sqlStr,String whereID){
        System.out.println("ProduceMaxBh.sqlStr :" + sqlStr);
        util.RetrieveObject reobject = new RetrieveObject();
        java.util.Vector vdata = null;
        Object obj = null;
        vdata = reobject.getObjectRow(sqlStr);
        obj = vdata.get(0);
        String maxbh = null,newbh = null;

        if (obj == null){
            newbh = whereID + "01";
        }else{
            maxbh = String.valueOf(vdata.get(0));
            System.out.println("maxbh = " + maxbh);
            //String subStr = maxbh.substring(maxbh.length() - 1 ,maxbh.length());
            //String subStr = maxbh.substring(maxbh.length() ,maxbh.length());

            String subStr = String.valueOf(Integer.parseInt(maxbh) + 1);
            System.out.println(subStr);
            if ((subStr.length() == 1) ||(subStr.length() == 3)||(subStr.length() == 5)) {
                subStr = "0" + subStr;
                newbh = subStr;
            }else{
                newbh = whereID + subStr;
            }


            System.out.println("substr = " + subStr);
            System.out.println("newbh = " + newbh);
            //newbh = maxbh.substring(0,2) + "0" + subStr;

        }

        return newbh;
    }

}
