package tcp;

import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class ClientSide {
	private String hostname;
	private final static int port = 2680;
	private final static int Data_Size = 1024;
	private Socket clientSocket = null;
	DataOutputStream out = null;
	boolean flag = true;
	private long start_time = 0;
	private long end_time = 0;
	private long transfer_time = 0;

	public ClientSide(String hostname) {
		super();
		this.hostname = hostname;
	}

	private void CreateConnection() {
		try {
			clientSocket = new Socket(hostname, port);
			System.out.println("Connection established!");
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Connection failed!");
			flag = false;
		}
	}

	private void tearDownConnection() {
		try {
			if (out != null)
				out.close();
			if (clientSocket != null)
				clientSocket.close();
		} catch (Exception e) {
			System.err.println("Error while closing socket!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// args[0] is the remote server ip address or hostname
		ClientSide client = new ClientSide(args[0]);
		client.CreateConnection();
		Scanner scan = null;
		while (client.flag) {
			// Get the desired file path and name residing in the server
			System.out.println("Please type in file path and name you want to upload: ");
			scan = new Scanner(System.in);
			String filename = scan.nextLine();

			File file = new File(filename);
			if (file.exists() && file.isFile()) {
				// Transfer file size and filename
				try {
					client.out.writeLong(file.length());
					client.out.flush();
					client.out.writeUTF(filename);
					client.out.flush();
				} catch (IOException e) {
					System.err.println("Error while transfering filesize and filename to server!");
					e.printStackTrace();
				}

				// Transfer file itself
				client.start_time = System.currentTimeMillis();
				int size = 0;
				byte[] bs = new byte[Data_Size];
				DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
				while ((size = fis.read(bs)) != -1) {
					client.out.write(bs, 0, size);
					bs = new byte[Data_Size];
				}
				client.out.flush();
				System.out.println("File " + file.getName() + " transfer has completed!");
				client.end_time = System.currentTimeMillis();
				client.transfer_time = client.end_time - client.start_time;
				System.out.println("Transfer costs " + client.transfer_time + " milliseconds.");
				fis.close();
			} else {
				System.out.println("Cannot find file " + filename + "! Please try another!");
			}
			// Whether to continue
			System.out.print("Continue(Y/N): ");
			String msg = scan.nextLine();
			if (msg.equals("N") || msg.equals("n")) {
				client.tearDownConnection();
				client.flag = false;
				System.out.println("Connection has been closed!");
			}
		}
		if (scan != null)
			scan.close();
	}
}
