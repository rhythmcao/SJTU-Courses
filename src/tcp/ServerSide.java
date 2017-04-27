package tcp;

import java.io.*;
import java.net.*;

public class ServerSide {
	private final static int port = 2680;
	private final static int Data_Size = 1024;

	public static void main(String[] args) {
		try {
			String hostname = null;
			if (args.length == 0)
				hostname = "localhost";
			else
				hostname = args[0];
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket();
			InetSocketAddress address = new InetSocketAddress(hostname, port);
			server.bind(address);
			while (true) {
				System.out.println("Waiting to be connected...");
				Socket connection = server.accept();
				Thread task = new FileThread(connection);
				task.start();
			}
		} catch (IOException ex) {
			System.err.println("Couldn't start server! Port " + port + " is occupied!");
		}
	}

	private static class FileThread extends Thread {
		private Socket connection = null;

		public FileThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			try {
				DataInputStream dis = new DataInputStream(connection.getInputStream());
				while (true) {
					// First transfer the filesize, then filename, finally file itself
					long filesize = dis.readLong();
					String filename = dis.readUTF();

					File filepath = new File(filename);
					File file=new File(filepath.getName());
					System.out.println("Client " + connection.getInetAddress() + " wants to upload " + file + ".");
					// Create local file
					int count = 1;
					while (file.exists() && file.isFile()) {
						file = new File("(" + count + ")" + filepath.getName());
						count = count + 1;
					} 
					file.createNewFile();
					
					// Ready to receive file
					long length = 0;
					int size = 0;
					DataOutputStream fos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
					byte[] bs = new byte[Data_Size];
					while ((size = dis.read(bs)) != -1) {
						fos.write(bs);
						length = length + size;
						if (length >= filesize)
							break;
					}
					System.out.println("File has been saved to " + file.getAbsolutePath() + ".");
					fos.close();
				}
			} catch (IOException e) {
				
			}
		}
	}
}
