package clientServerModel;

import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class ClientSide {
	private String hostname;
	private final static int port = 2680;
	private final static int Data_Size = 1024 * 60;
	private Socket clientSocket = null;
	DataOutputStream out = null;
	DataInputStream in = null;
	File file = null;
	FileOutputStream fos = null;
	boolean flag = true;

	public ClientSide(String hostname) {
		super();
		this.hostname = hostname;
	}

	private void CreateConnection() {
		try {
			clientSocket = new Socket(hostname, port);
			System.out.println("Connection established!");
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream(), Data_Size));
		} catch (IOException e) {
			System.out.println("Connection failed!");
			flag = false;
			e.printStackTrace();
		}
	}

	private void createLocalFile(String remoteFile) {
		// Create a new file in localhost with default file name
		file = new File(remoteFile);
		String filename = file.getName();
		file = new File(filename);
		int count = 0;
		while (file.exists()) {
			file = new File(count + "_" + filename); 
			// If file already exists, rename it by a number.
			count++;
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println("Error while creating new file in localhost!");
			e.printStackTrace();
		}
	}

	private void receiveFile(String remoteFile) {
		try {
			long length = in.readLong();
			if (length > 0) {
				this.createLocalFile(remoteFile);
				System.out.println("Size of the desired file is " + length);
				System.out.println("File receiving started!");
				fos = new FileOutputStream(file);
				int size = 0;
				int current = 0;
				byte[] bs = new byte[Data_Size];
				while ((size = in.read(bs)) != -1) {
					fos.write(bs, 0, size);
					bs = new byte[Data_Size];
					current += size;
					if (current >= length) {
						System.out.println("File receiving completed!");
						break;
					}
				}
				System.out.println("File has been saved to " + file.getAbsolutePath() + ".");
				if (fos != null)
					fos.close();
			} else {
				System.out.println("The desired file doesn't exist!");
			}
		} catch (IOException e) {
			System.err.println("Error while receiving desired file!");
			e.printStackTrace();
		}
	}

	private void tearDownConnection() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (clientSocket != null)
				clientSocket.close();
		} catch (Exception e) {
			System.err.println("Error while closing socket!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		ClientSide client = new ClientSide(args[0]);
		client.CreateConnection();
		Scanner scan = null;
		while (client.flag) {
			// Get the desired file path and name residing in the server
			System.out.println("Please type in file path and name:");
			scan = new Scanner(System.in);
			String file = scan.nextLine();

			// Transfer file name
			try {
				client.out.writeUTF(file);
			} catch (IOException e) {
				System.err.println("Error while transfering filename to be downloaded!");
				e.printStackTrace();
			}

			// Receive and write file
			client.receiveFile(file);

			// Whether to continue
			System.out.print("Continue(Y/N): ");
			String msg = scan.nextLine();
			if (msg.equals("N") || msg.equals("n")) {
				client.tearDownConnection();
				client.flag = false;
				System.out.println("Connection has been closed!");
			}
		}
		if(scan!=null)
			scan.close();
	}
}
