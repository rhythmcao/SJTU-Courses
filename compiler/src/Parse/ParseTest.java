package Parse;
import java.io.*;

public class ParseTest {
  public static void main(String argv[])  {
      String filename = "testcase"+File.separator+"Good"+File.separator+"test1.tig";
      Parse parse=new Parse(filename);
      PrintStream astOut = new PrintStream(System.out);
      Absyn.Print output=new Absyn.Print(astOut);
      output.prExp(parse.absyn,0);
      System.out.println("\n");
      System.out.println("***************************");
      System.out.println("         Finished!         ");
      System.out.println("***************************");
      astOut.close();
	try {
		PrintStream ps= new PrintStream(new FileOutputStream(new File("absOut.abs")));
		Absyn.Print toFile=new Absyn.Print(ps);
		toFile.prExp(parse.absyn,0);
		ps.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
      
  }

}


