package util;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.util.Vector;
public class ProduceMaxBh {
    public ProduceMaxBh() {
		super();
	}
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
            String subStr = String.valueOf(Integer.parseInt(maxbh) + 1);
            if (subStr.length() %2 == 1) {
                subStr = "0" + subStr;
                newbh = subStr;
            }else{
                newbh = whereID + subStr;
            }
        }
        return newbh;
    }

}
