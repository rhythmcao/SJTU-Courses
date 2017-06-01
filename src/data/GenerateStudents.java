package data;
import java.sql.*;
import java.util.Random;
public class GenerateStudents {
	//һ����60�ˣ�120���࣬����7200�ˣ�8��9��10��10
	public static final String[] familyName={"��","��","��","��","��","��","��","��"};
	public static final String[] firstName1={"��","ͮ","��","��","��","��","��","�","��","��"};
	public static final String[] firstName2={"��","��","ѩ","��","�","��","��","��","Ľ","��"};
	public static final String[] firstName3={"��","ï","��","��","��","��","��","��","��"};
	public static final String[] gender={"��","Ů"};
	public static final String[] mail={"@sohu.com","@qq.com","@gmail.com","@163.com","@126.com","@sina.com"};
	public static int index1=-1;
	public static int index2=0;
	public static int index3=0;
	public static int index4=0;
	public static final int student_num=60;//һ����60��
	Connection conn=null;
	Statement stmt=null;
	public GenerateStudents(Connection conn, Statement stmt) {
		super();
		this.conn = conn;
		this.stmt = stmt;
	}
	public String getSex(){
		Random rand=new Random();
		return gender[rand.nextInt(gender.length)];
	}
	public int getAge(){
		int max=20,min=10;
		Random rand=new Random();
		return (int)(rand.nextInt(max)%(max-min+1) + min);
	}
	public String getPhoneNumber(){
		int i=0;
		Random rand=new Random();
		String phone="1";
		for(i=0;i<10;++i)
			phone=phone+String.valueOf(rand.nextInt(10));
		return phone;
	}
	public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // �����ĸ��������
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
            // �ַ���
            if ("char".equalsIgnoreCase(charOrNum)) {
                // ȡ�ô�д��ĸ����Сд��ĸ
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // ����
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
	public String getEmail(){
		Random rand=new Random();
		return getCharAndNumr(rand.nextInt(10))+mail[rand.nextInt(mail.length)];
	}

	public static String getNextName(){
		if((index1+1)!=familyName.length){
			index1+=1;
		}else{
			index1=0;
			if((index2+1)!=firstName1.length){
				index2+=1;
			}else{
				index2=0;
				if((index3+1)!=firstName2.length){
					index3+=1;
				}else{
					index3=0;
					index4=(index4+1)%firstName3.length;
				}
			}
		}
		return familyName[index1]+firstName1[index2]+firstName2[index3]+firstName3[index4];	
	}
	public void AutoGenerateStudents() throws SQLException{
		Statement mystmt=conn.createStatement();
		String classID=null;
		String stuid=null;
		String sql="select distinct classID from tb_classinfo";
		int i=0,j=0;
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()){
			classID=rs.getString(1);
			for(i=1;i<=student_num;++i){
				if(i<10)
					stuid=classID+"0"+String.valueOf(i);
				else
					stuid=classID+String.valueOf(i);
				sql="insert into tb_studentinfo(stuid,classID,stuname,sex,age,addr,phone,email) values ('"
						+stuid+"','"
						+classID+"','"
						+getNextName()+"','"
						+getSex()+"',"
						+getAge()+",'"
						+RandomAddress.getAddress()+"','"
						+getPhoneNumber()+"','"
						+getEmail()+"')";
				mystmt.executeUpdate(sql);
				j+=1;
			}
		}
		System.out.println("Totally "+j+" students are inserted!");
		
	}
	
}
