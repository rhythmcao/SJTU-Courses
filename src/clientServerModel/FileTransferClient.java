package clientServerModel;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class FileTransferClient extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button bt1,bt2;
	TextField tf1,tf2;
	
	public FileTransferClient() throws HeadlessException {
		super();
		initial();
	}

	public FileTransferClient(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public FileTransferClient(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	public FileTransferClient(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public void initial() {
		setTitle("File Sharing--Client");
		setBounds(100, 80, 500, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menu mn=new Menu("Client");
		MenuBar mb=new MenuBar();
		mb.add(mn);
		Menu m=new Menu("hhe");
		mb.add(m);
		MenuItem it1,it2,it3;
		it1=new MenuItem("file");
		it2=new MenuItem("edit");
		it3=new MenuItem("configuration");
		mn.add(it1);
		mn.add(it2);
		mn.add(it3);
		setMenuBar(mb);
		bt1 = new Button("Connect");
		bt2 = new Button("SaveTo");
		Container con=getContentPane();
		con.add(bt1);
		con.add(bt2);
		tf1=new TextField(15);
		tf2=new TextField(15);
		tf2.setEchoChar('*');
		con.add(new JLabel("user"));
		con.add(tf1);
		con.add(new JLabel("password"));
		con.add(tf2);
		validate();
	}

	public static void main(String[] args) throws InterruptedException{
		JFrame cl=new FileTransferClient();
		cl.validate();
		System.out.println(cl.getState()==Frame.NORMAL);
		Thread.sleep(10000000);
//		File f=new File("D:\\hello.txt");
//		String filename="try.txt";
//		File w=new File(filename);
//		if(w.exists())
//			System.out.println("File exist!");
//		else{
//			w.createNewFile();
//		}
//		DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
//		FileOutputStream fos=new FileOutputStream(w);
//		int size=0;
//		byte[] bs = new byte[Data_Size];
//		while((size=fis.read(bs))!=-1){
//			fos.write(bs,0,size);
//			bs = new byte[Data_Size];
//		}
//		fos.flush();
//		if(fis!=null)
//			fis.close();
//		if(fos!=null)
//			fos.close();
	}
//	private final static int port = 2690;
//	public static void main(String[] args) {
//		try {
//			@SuppressWarnings("resource")
//			ServerSocket server = new ServerSocket(port);
//			while (true) {
//				System.out.println("Waiting to be connected...");
//				Socket connection = server.accept();
//				Thread task = new FileThread(connection);
//				task.start();
//			}
//		} catch (IOException ex) {
//			System.err.println("Couldn't start server!");
//		}
//	}
//
//	private static class FileThread extends Thread {
//		private Socket connection = null;
//
//		public FileThread(Socket connection) {
//			this.connection = connection;
//		}
//
//		public void run() {
//			try {
//				DataInputStream dis = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
//				DataOutputStream ps = new DataOutputStream(connection.getOutputStream());
//				while (true) {
//					String file = dis.readUTF();
//					System.out.println(file);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}

