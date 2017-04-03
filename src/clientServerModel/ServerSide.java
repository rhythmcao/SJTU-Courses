package clientServerModel;

import java.io.*;
import java.net.*;

public class ServerSide {
	private final static int port = 2680;
	private final static int Data_Size = 1024 * 60;
	public static void main(String[] args) {
		
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket();
			InetSocketAddress address=new InetSocketAddress("localhost", port);
			server.bind(address);
			while (true) {
				System.out.println("Waiting to be connected...");
				Socket connection = server.accept();
				Thread task = new FileThread(connection);
				task.start();
			}
		} catch (IOException ex) {
			System.err.println("Couldn't start server! Port "+port+" is occupied!");
		}
	}

	private static class FileThread extends Thread {
		private Socket connection = null;

		public FileThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			try {
				DataOutputStream ps = new DataOutputStream(connection.getOutputStream());
				DataInputStream commands=new DataInputStream(connection.getInputStream());
				while (true) {
					String file = commands.readUTF();
					System.out.println("Client "+connection.getInetAddress()+" request "+file+".");
					File filePath = new File(file);
					long length=0;
					if (filePath.exists()) {
						length = (long)filePath.length();
						ps.writeLong(length);
						ps.flush();
						DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
						int size=0;
						byte[] bs = new byte[Data_Size];
						while((size=fis.read(bs))!=-1){
							ps.write(bs,0,size);
							bs = new byte[Data_Size];
						}
						ps.flush();
						System.out.println("File "+filePath.getName()+" transferring to Client "+connection.getInetAddress()+" completed!");
						if(fis!=null)
							fis.close();
					}
					else{
						ps.writeLong(0);
						System.out.println("No such file. Nothing to be transfered to "+connection.getInetAddress());
					}	
				}
			} catch (IOException e) {
				System.out.println("One client terminates its connection!");
			}
		}
	}
}
