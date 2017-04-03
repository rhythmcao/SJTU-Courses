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
	private static final long serialVersionUID = 1L;
	Button bt1,bt2,bt3,bt4,bt5,bt6;
	JTextField tf1,tf2, tf3;
	Box basebox, box1,box2,box3,box4;
	Box wholebox;
	JTextArea jta;
	public FileTransferClient() throws HeadlessException {
		super();
		initial();
	}

	public FileTransferClient(GraphicsConfiguration gc) {
		super(gc);
	}

	public FileTransferClient(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public FileTransferClient(String title) throws HeadlessException {
		super(title);
	}

	public void initial() {
		setTitle("File Sharing--Client");
		setBounds(100, 80, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int vertical=20, horizontal=10;
		box1=Box.createVerticalBox();
		JLabel j1=new JLabel("Server IP: ");
		j1.setFont(new Font("TimesRoman",Font.BOLD,18));
		box1.add(j1);
		box1.add(Box.createVerticalStrut(vertical));
		JLabel j2=new JLabel("File Path and Name: ");
		j2.setFont(new Font("TimesRoman",Font.BOLD,18));
		box1.add(j2);
		box1.add(Box.createVerticalStrut(vertical));
		JLabel j3=new JLabel("Save Path and Name: ");
		j3.setFont(new Font("TimesRoman",Font.BOLD,18));
		box1.add(j3);
		
		box2=Box.createVerticalBox();
		tf1=new JTextField("127.0.0.1",30);
		tf1.setEditable(true);
		tf1.setFont(new Font("TimesRoman",Font.ITALIC,15));
		tf2=new JTextField("D:\\Hello.txt",30);
		tf2.setEditable(true);
		tf2.setFont(new Font("TimesRoman",Font.ITALIC,15));
		tf3=new JTextField("E:\\Hello.txt",30);
		tf3.setEditable(true);
		tf3.setFont(new Font("TimesRoman",Font.ITALIC,15));
		box2.add(tf1);
		box2.add(Box.createVerticalStrut(vertical));
		box2.add(tf2);
		box2.add(Box.createVerticalStrut(vertical));
		box2.add(tf3);
		
		box3=Box.createVerticalBox();
		bt1 = new Button("Connect");
		bt1.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt2 = new Button("Verify");
		bt2.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt3 = new Button("Download");
		bt3.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		box3.add(bt1);
		box3.add(Box.createVerticalStrut(vertical));
		box3.add(bt2);
		box3.add(Box.createVerticalStrut(vertical));
		box3.add(bt3);
		
		box4=Box.createVerticalBox();
		bt4 = new Button("Disconnect");
		bt4.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt5 = new Button("Clear");
		bt5.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt6 = new Button("Clear");
		bt6.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		box4.add(bt4);
		box4.add(Box.createVerticalStrut(vertical));
		box4.add(bt5);
		box4.add(Box.createVerticalStrut(vertical));
		box4.add(bt6);
		
		basebox=Box.createHorizontalBox();
		basebox.add(box1);
		basebox.add(Box.createHorizontalStrut(horizontal));
		basebox.add(box2);
		basebox.add(Box.createHorizontalStrut(horizontal));
		basebox.add(box3);
		basebox.add(Box.createHorizontalStrut(horizontal));
		basebox.add(box4);
		
		jta=new JTextArea(40,30);
		jta.setEditable(false);
		jta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
		jta.setFont(new Font("TimeRoman",Font.PLAIN,15));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setMargin(new Insets(100,100,10,10));
		jta.setText(" Please type in the Server IP and try to connect first ......");
		JScrollPane jsp=new JScrollPane(jta);
		wholebox=Box.createVerticalBox();
		wholebox.add(basebox);
		wholebox.add(Box.createVerticalStrut(vertical));
		wholebox.add(jsp);
		
		Container con=getContentPane();
		con.setLayout(new FlowLayout());
		con.add(wholebox);
		con.validate();
	}

	public static void main(String[] args) throws InterruptedException{
		JFrame cl=new FileTransferClient();
		String s=null;
		System.out.println(s.equals(null));
}
}

