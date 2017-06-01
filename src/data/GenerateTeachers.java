package data;
import java.sql.*;
import java.util.Random;
public class GenerateTeachers {
	// 一共120个班，每个班配四个老师，共计480个老师，8×10×6
	public static final String[] familyName={"赵","钱","孙","李","周","吴","郑","王"};
	public static final String[] firstName1={"嘉","煜","伟","弘","俊","卫","立","智","浩","静"};
	public static final String[] firstName2={"宏","鹏","杰","文","雨","瑞"};
	public static final String[] gender={"男","女"};
	public static final String[] knowLevel={"助教","初级教师","中级教师","高级教师","特级教师"};
	public static final String[] knowLedge={"中专","大学专科","大学本科","硕士","博士"};
	public static final int teacher_num=4;//一个班4个老师
	public static int index1=0;
	public static int index2=0;
	public static int index3=-1;
	Connection conn=null;
	Statement stmt=null;
	String sql=null;
	public GenerateTeachers(Connection conn, Statement stmt) {
		super();
		this.conn = conn;
		this.stmt = stmt;
	}
	
	public String getSex(){
		Random rand=new Random();
		return gender[rand.nextInt(gender.length)];
	}
	
	public String getKnowledge(){
		Random rand=new Random();
		return knowLedge[rand.nextInt(knowLedge.length)];
	}
	
	public String getKnowlevel(){
		Random rand=new Random();
		return knowLevel[rand.nextInt(knowLevel.length)];
	}
	
	public static String getNextName(){
		if((index3+1)!=firstName2.length){
			index3+=1;
		}else{
			index3=0;
			if((index2+1)!=firstName1.length){
				index2+=1;
			}else{
				index2=0;
				index1=(index1+1)%familyName.length;
			}
		}
		return familyName[index1]+firstName1[index2]+firstName2[index3];	
			
	}
	public void AutoGenerateTeachers() throws SQLException{
		Statement mystmt=conn.createStatement();
		String teaid=null;
		String classID=null;
		String teaname=null;
		String sex=null;
		String knowledge=null;
		String knowlevel=null;
		sql="select distinct classID from tb_classinfo";
		ResultSet rs=stmt.executeQuery(sql);
		int j=0;
		while(rs.next()){
			classID=rs.getString(1).trim();
			int i=0;
			for(i=0;i<teacher_num;++i){
				teaid=String.valueOf(i+1)+classID;
				teaname=getNextName();
				sex=getSex();
				knowledge=getKnowledge();
				knowlevel=getKnowlevel();
				sql="insert into tb_teacher(teaid,classID,teaname,sex,knowledge,knowlevel) values ('"+teaid+"','"+classID+"','"+teaname+"','"+sex+"','"+knowledge+"','"+knowlevel+"')";
				mystmt.executeUpdate(sql);
				j++;
			}
		}
		if(mystmt!=null)
			mystmt.close();
		System.out.println("Totally "+j+" teachers are inserted!");
	}
}
