package rdtudp;

/**
 * FileSender : A client that uploads a file to a server.
 *
 * Usage: java FileSender <hostname> <filepath> 
 *
 * This program connects to FileReceiver running on a given
 * hostname and at a given port number, and uploads the file
 * given by filename.
 */
import java.io.*;

public class FileSender {
	private final static int Data_Size = 1024;
	private final static int port=2680;
	public static void main(String args[]) {
		// Parse the command line argument.
		if (args.length < 2) {
			System.err.println("Missing arguments!");
			return;
		}
		String hostname = args[0];
		String filename = args[1];
		try {
			// Create new reliable transport object and file input stream.
			RDTSender rdt = new RDTSender(hostname, port);
			File file=new File(filename);
			if(!(file.exists()&&file.isFile()))
					throw new FileNotFoundException();
			FileInputStream fis = new FileInputStream(file);

			// Repeatedly read from the file and upload to the server.
			long startTime = System.currentTimeMillis();
			byte data[] = new byte[Data_Size];
			while (true) {
				int length = fis.read(data, 0, Data_Size);
				if (length == -1)
					break;
				rdt.send(data, length);
			}
			long endTime = System.currentTimeMillis();
			long delayTime = endTime - startTime;
			rdt.close();
			System.out.println("Total delay time is "+delayTime+"milliseconds.");
			fis.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
