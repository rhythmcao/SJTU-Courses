package util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.*;
import javax.swing.JOptionPane;
import java.sql.*;
import view.JF_view_error;
public class JdbcAdapter {
    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String infoStr = null;

    public boolean BuildeDeleteTempView(String sqlState){
            boolean flag = false;
            System.out.println("执行的语句为:" + sqlState);
            try{
                con = CommonaJdbc.conection;
                pstmt = con.prepareStatement(sqlState);
                pstmt.execute();
                flag = true;
            }catch(java.sql.SQLException sql){
                flag = false;
                sql.printStackTrace();
            }
            return flag;
    }
    //真正的执行对数据库的各种操作
    private boolean AdapterObject(String sqlState){
        boolean flag = false;
        System.out.println("执行的语句为:" + sqlState);
        try{
            con = CommonaJdbc.conection;
            pstmt = con.prepareStatement(sqlState);
            pstmt.execute();
            flag = true;
            JOptionPane.showMessageDialog(null,infoStr + "数据成功!!!","系统提示",JOptionPane.INFORMATION_MESSAGE);
        }catch(java.sql.SQLException sql){
            view.JF_view_error error = new JF_view_error("执行的SQL语句为:\n" + sqlState + "\n错误信息为：" + sql.getMessage());
            flag = false;
            sql.printStackTrace();
        }
        return flag;
    }
    //执行删除数据表中的数据
    public boolean DeleteObject(String deleteSql){
        infoStr = "删除";
        return AdapterObject(deleteSql);
    }

    //验证数据表中是否存在数据
    private boolean validateID(String id,String tname,String idvalue){
        String sqlStr = null;
        sqlStr = "select count(*) from " + tname + " where "
                 +  id + " = '" + idvalue + "'";
        try{
            con = CommonaJdbc.conection;
            pstmt = con.prepareStatement(sqlStr);
            java.sql.ResultSet rs = null;
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0)   return true;
            }
        }catch(java.sql.SQLException sql){
            new view.JF_view_error("执行的SQL语句为:\n" + sqlStr + "\n错误信息为：" +sql.getMessage());
           sql.printStackTrace();
           return false;
        }
        return false;
    }

    public boolean InsertOrUpdateObject(Obj_gradeinfo objgradeinfo){
        String sqlStatement = null;
        if (validateID("gradeID","tb_gradeinfo",objgradeinfo.getGradeID().trim())){
            sqlStatement = "Update tb_gradeinfo set gradeID = '"
                       + objgradeinfo.getGradeID() + "',gradeName ='" + objgradeinfo.getGradeName()
                       + "' where gradeID = '" + objgradeinfo.getGradeID().trim() + "'";
            infoStr = "更新年级";
        }else{
            sqlStatement = "Insert into tb_gradeinfo(gradeID,gradeName) values ('"
                        + objgradeinfo.getGradeID() + "','" + objgradeinfo.getGradeName()
                        + "')";
           infoStr = "添加年级";
        }
        System.out.println(sqlStatement);
        return AdapterObject(sqlStatement);
    }

    public boolean InsertOrUpdateObject(Obj_classinfo objclassinfo){
        String sqlStatement = null;
        if (validateID("classID","tb_classinfo",objclassinfo.getClassID())){
            sqlStatement = "Update tb_classinfo set classID = '" + objclassinfo.getClassID()
                           + "',gradeID = '" + objclassinfo.getGradeID()
                           + "', className = '" + objclassinfo.getClassName()
                           + "' where classID = '" + objclassinfo.getClassID().trim() + "'";
            infoStr = "更新班级";
        }else{
            sqlStatement = "Insert into tb_classinfo(classID,gradeID,className) values ('"
                       + objclassinfo.getClassID() + "','" + objclassinfo.getGradeID()
                       + "','" + objclassinfo.getClassName() + "')";
           infoStr = "添加班级";
        }
        System.out.println(sqlStatement);
        return AdapterObject(sqlStatement);
    }
    public boolean InsertOrUpdateObject(Obj_subject objsubject){
        String sqlStatement = null;
        if (validateID("code","tb_subject",objsubject.getCode())){
            sqlStatement = "Update tb_subject set code = '" + objsubject.getCode()
                           + "',subject = '" + objsubject.getSubject()
                           + "' where code = '" + objsubject.getCode().trim() + "'";
            infoStr = "更新考试科目";

        }else{
            sqlStatement = "Insert into tb_subject(code,subject) values ('"
                       + objsubject.getCode() + "','" + objsubject.getSubject()
                       + "')";
            infoStr = "添加考试科目";
        }

        return AdapterObject(sqlStatement);

    }
    public boolean InsertOrUpdateObject(Obj_examkinds objexamkinds){
        String sqlStatement = null;
        if (validateID("KindID","tb_examkinds",objexamkinds.getKindID())){
            sqlStatement = "Update tb_examkinds set KindID = '" + objexamkinds.getKindID()
                           + "',KindName = '" +  objexamkinds.getKindName()
                           + "' where KindID = '" + objexamkinds.getKindID().trim() + "'";

            infoStr = "更新考试类别";

        }else{
            sqlStatement = "Insert into tb_examkinds(KindID,KindName) values ('"
                           + objexamkinds.getKindID() + "','" +
                           objexamkinds.getKindName()
                           + "')";
            infoStr = "添加考试类别";
        }
        return AdapterObject(sqlStatement);
    }
    public boolean InsertOrUpdateObject(Obj_user objuser){
        String sqlStatement = null;
        if (validateID("userid","tb_user",objuser.getUserid())){
            sqlStatement = "Update tb_user set userid = '" + objuser.getUserid()
                           + "',username = '" + objuser.getUsername()
                           + "',pass = '" + objuser.getPass()
                           + "' where userid = '" + objuser.getUserid().trim() + "'";
            infoStr = "更新用户";

        }else{
            sqlStatement = "Insert into tb_user(userid,username,pass) values ('"
                       + objuser.getUserid() + "','" + objuser.getUsername() + "','" + objuser.getPass()
                       + "')";
            infoStr = "添加用户";
        }

        return AdapterObject(sqlStatement);

    }
    //产生更新存盘学生信息语句
    public boolean InsertOrUpdateObject(Obj_student objstudent){
        String sqlStatement = null;
        if (validateID("stuid","tb_studentinfo",objstudent.getStuid())){
            sqlStatement = "Update tb_studentinfo set stuid = '" + objstudent.getStuid()
                           + "',classID = '" +  objstudent.getClassID()
                           + "',stuname = '" + objstudent.getStuname()
                           + "',sex = '" + objstudent.getSex()
                           + "',age = '" + objstudent.getAge()
                           + "',addr = '" + objstudent.getAddress()
                           + "',phone = '" + objstudent.getPhone()
                           + "',email ='"+objstudent.getEmail()
                           + "' where stuid = '" + objstudent.getStuid().trim() + "'";

            infoStr = "更新学生信息";

        }else{
            sqlStatement = "Insert into tb_studentinfo(stuid,classid,stuname,sex,age,addr,phone,email) values ('" + objstudent.getStuid()
                           + "','" +  objstudent.getClassID()
                           + "','" + objstudent.getStuname()
                           + "','" + objstudent.getSex()
                           + "','" + objstudent.getAge()
                           + "','" + objstudent.getAddress()
                           + "','" + objstudent.getPhone()
                           + "','" + objstudent.getEmail()
                           + "')";

            infoStr = "添加学生信息";
        }
        return AdapterObject(sqlStatement);
    }

    //产生更新存盘教师信息语句
    public boolean InsertOrUpdateObject(Obj_teacher objteacher){
        String sqlStatement = null;
        if (validateID("teaid","tb_teacher",objteacher.getTeaid())){
            sqlStatement = "Update tb_teacher set teaid = '" + objteacher.getTeaid()
                           + "',classID = '" +  objteacher.getClassID()
                           + "',teaname = '" + objteacher.getTeaname()
                           + "',sex = '" + objteacher.getSex()
                           + "',knowledge = '" + objteacher.getKnowledge()
                           + "',knowlevel = '" + objteacher.getKnowlevel()
                           + "' where teaid = '" + objteacher.getTeaid().trim() + "'";

            infoStr = "更新教师信息";

        }else{
            sqlStatement = "Insert into tb_teacher(teaid,classID,teaname,sex,knowledge,knowlevel) values ('" + objteacher.getTeaid()
                           + "','" +  objteacher.getClassID()
                           + "','" + objteacher.getTeaname()
                           + "','" + objteacher.getSex()
                           + "','" + objteacher.getKnowledge()
                           + "','" + objteacher.getKnowlevel()
                           + "')";

            infoStr = "添加教师信息";
        }
        return AdapterObject(sqlStatement);
    }
    //验证数据表中是否存在数据
    private boolean validateobjgradeinfo(String stuid,String kindid,String code){
        String sqlStr = null;
        sqlStr = "select count(*) from tb_gradeinfo_sub where stuid = '"
                 +  stuid  + "' and kindID = '" + kindid + "' and code = '" + code + "'";
        System.out.println(sqlStr);
        try{
            con = CommonaJdbc.conection;
            pstmt = con.prepareStatement(sqlStr);
            java.sql.ResultSet rs = null;
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0)   return true;
            }
        }catch(java.sql.SQLException sql){
             sql.printStackTrace();
          new view.JF_view_error("执行的SQL语句为:\n" + sqlStr + "\n错误信息为：" +sql.getMessage());

           return false;
        }

        return false;
    }

    //产生更新学生信息信息语句
    public boolean InsertOrUpdate_Obj_gradeinfo_sub(Obj_gradeinfo_sub[] object){
        try{
            con = CommonaJdbc.conection;
            stmt = con.createStatement();

            for (int i = 0; i < object.length; i++) {
                String sqlStr = null;
                if (validateobjgradeinfo(object[i].getStuid(),object[i].getKindID(),object[i].getCode())){
                    sqlStr =
                        "update tb_gradeinfo_sub set stuid = '" +
                        object[i].getStuid() + "',stuname = '" + object[i].getSutname() +
                        "',kindID = '" + object[i].getKindID() + "',code = '" +object[i].getCode() +
                        "',grade = " + object[i].getGrade() + " ,examdate = '" +
                        object[i].getExamdate() +"' where stuid = '"
                        +  object[i].getStuid()  + "' and kindID = '" + object[i].getKindID() + "' and code = '" + object[i].getCode() + "'";

                }else{
                    sqlStr =
                       "insert  into tb_gradeinfo_sub(stuid,stuname,kindID,code,grade,examdate)  values ('" +
                       object[i].getStuid() + "','" + object[i].getSutname() +
                       "','" + object[i].getKindID() + "','" +
                       object[i].getCode() +
                       "'," + object[i].getGrade() + " ,'" +
                       object[i].getExamdate() +
                       "')";
                }

                System.out.println("sqlStr = " + sqlStr);

                stmt.addBatch(sqlStr);
            }
            stmt.executeBatch();
            JOptionPane.showMessageDialog(null, "学生成绩数据存盘成功!!!", "系统提示",
                                          JOptionPane.INFORMATION_MESSAGE);
        }catch(java.sql.SQLException sqlerror) {
            new view.JF_view_error("错误信息为：" +sqlerror.getMessage());
            return false;
        }
        return true;
    }
    //产生更新学生信息语句
    public boolean Delete_Obj_gradeinfo_sub(Obj_gradeinfo_sub[] object){
        try{
            con = CommonaJdbc.conection;
            stmt = con.createStatement();

            for (int i = 0; i < object.length; i++) {
                String sqlStr = null;
                sqlStr = "Delete From tb_gradeinfo_sub  where stuid = '"
                        +  object[i].getStuid()  + "' and kindID = '"
                        + object[i].getKindID() + "' and code = '"
                        + object[i].getCode() + "'";
                System.out.println("sqlStr = " + sqlStr);
                stmt.addBatch(sqlStr);
            }
            stmt.executeBatch();
            JOptionPane.showMessageDialog(null, "学生成绩数据数据删除成功!!!", "系统提示",
                                          JOptionPane.INFORMATION_MESSAGE);
        }catch(java.sql.SQLException sqlerror) {
            new view.JF_view_error("错误信息为：" +sqlerror.getMessage());
            return false;
        }
        return true;
    }


}
