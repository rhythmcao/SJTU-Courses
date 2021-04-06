package rdtudp;
/**
 * FileReceiver : A file server that receives a file.
 *
 * This program waits for the client to connect and upload one
 * file, which will be saved as the given filename given in the
 * second command line argument.
 */
import java.io.*;

public class FileReceiver {
	private final static int port = 2680;
	public static void main(String args[]) {
		String filename = "sample";
		try {
			// Create new reliable transport object and file output stream.
			RDTReceiver rdt = new RDTReceiver(port);
			File filepath=new File(filename+"(0)");
			int count=1;
			while(filepath.exists()&&filepath.isFile()){
				filepath=new File(filename+"("+count+")");
				count=count+1;
			}
			filepath.createNewFile();	
			FileOutputStream fos = new FileOutputStream(filepath);
			// Repeatedly receive data from the reliable transport until EOF
			byte data[] = rdt.recv();
			while (data != null) {
				fos.write(data);
				data = rdt.recv();
			}
			System.out.println("R: DONE!");
			rdt.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
