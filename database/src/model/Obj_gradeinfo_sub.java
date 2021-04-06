package model;

import java.sql.Date;


public class Obj_gradeinfo_sub {
    private String stuid;
    private String kindID;
    private String code;
    private float grade;
    private Date examdate;
    private String sutname;
    public String getStuid() {
        return stuid;
    }

    public String getKindID() {
        return kindID;
    }

    public String getCode() {
        return code;
    }

    public float getGrade() {
        return grade;
    }

    public Date getExamdate() {
        return examdate;
    }

    public String getSutname() {
        return sutname;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public void setKindID(String kindID) {
        this.kindID = kindID;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setExamdate(Date examdate) {
        this.examdate = examdate;
    }

    public void setSutname(String sutname) {
        this.sutname = sutname;
    }

}
