package p2pModel;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class P2Pmodel {
	public static final int port = 2680;
	public static final int Data_Size = 1024 * 60;

	public static void main(String[] args) {
		// Create an anoymous thread acting as a server
		new Thread() {
			public void run() {
				try {
					@SuppressWarnings("resource")
					ServerSocket server = new ServerSocket();
					InetSocketAddress address = new InetSocketAddress("localhost", port);
					server.bind(address);
					while (true) {
						// System.out.println("A server thread is created
						// waiting to be connected...");
						Socket connection = server.accept();
						Thread task = new P2Pmodel.ServerConnectionThread(connection);
						task.start();
					}
				} catch (IOException ex) {
					System.err.println("S: Port " + port + " is occupied!");
				}
			}
		}.start();
		
		// Client side specifies the hostname and file to be downloaded
		boolean flag = true;
		Scanner scan = null;
		while (flag) {
			System.out.println("C: Please input the hostname/ip address:");
			scan = new Scanner(System.in);
			String hostname = scan.nextLine();
			try {
				Socket p2p = new Socket(hostname, port);
				System.out.println("C: Connection established!");
				DataOutputStream out = new DataOutputStream(p2p.getOutputStream());
				DataInputStream in = new DataInputStream(new BufferedInputStream(p2p.getInputStream(), Data_Size));
				System.out.println("C: Please type in file path and name:");
				String file = scan.nextLine();
				out.writeUTF(file);
				out.flush();
				
				long length = in.readLong();
				if (length > 0) {
					System.out.println("C: Size of the desired file is " + length);
					System.out.println("C: File receiving started!");
					// Create a new file in localhost with default file name
					File df = new File(file);
					String filename = df.getName();
					df = new File(filename);
					int count = 0;
					while (df.exists()) {
						df = new File(count + "_" + filename);
						count++;
					}
					df.createNewFile();
					FileOutputStream fos = new FileOutputStream(df);
					
					int size = 0;
					int current = 0;
					byte[] bs = new byte[Data_Size];
					while ((size = in.read(bs)) != -1) {
						fos.write(bs, 0, size);
						bs = new byte[Data_Size];
						current += size;
						if (current >= length) {
							System.out.println("C: File receiving completed!");
							break;
						}
					}
					System.out.println("C: File has been saved to " + df.getAbsolutePath() + ".");
					if (fos != null)
						fos.close();
					if (out != null)
						out.close();
					if (in != null)
						in.close();
					if (p2p != null)
						p2p.close();
				} else {
					System.out.println("C: The desired file doesn't exist!");
					if (out != null)
						out.close();
					if (in != null)
						in.close();
					if (p2p != null)
						p2p.close();
				}
			} catch (UnknownHostException e) {
				System.err.println("C: The host is unknown!");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				System.out.print("C: Continue(Y/N): ");
				String msg = scan.nextLine();
				if (msg.equals("N") || msg.equals("n")) {
					flag = false;
					System.out.println("C: Closing the P2P file sharing process...");
				}
			}
		}
		if(scan!=null)
			scan.close();
		System.exit(0);
	}

	private static class ServerConnectionThread extends Thread {
		private Socket connection = null;

		public ServerConnectionThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			try {
				DataOutputStream ps = new DataOutputStream(connection.getOutputStream());
				DataInputStream commands = new DataInputStream(connection.getInputStream());
				while (true) {
					String file = commands.readUTF();
					System.out.println("S: Client " + connection.getInetAddress() + " request " + file + ".");
					File filePath = new File(file);
					long length = 0;
					if (filePath.exists()) {
						length = (long) filePath.length();
						ps.writeLong(length);
						ps.flush();
						DataInputStream fis = new DataInputStream(
								new BufferedInputStream(new FileInputStream(filePath)));
						int size = 0;
						byte[] bs = new byte[Data_Size];
						while ((size = fis.read(bs)) != -1) {
							ps.write(bs, 0, size);
							bs = new byte[Data_Size];
						}
						ps.flush();
						System.out.println("S: File " + filePath.getName() + " transferring to Client "
								+ connection.getInetAddress() + " completed!");
						if (fis != null)
							fis.close();
					} else {
						ps.writeLong(0);
						System.out.println("S: No such file. Nothing to be transfered to " + connection.getInetAddress());
					}
				}
			} catch (IOException e) {
//				System.out.println("S: One client terminates its connection!");
			}
		}
	}
}
