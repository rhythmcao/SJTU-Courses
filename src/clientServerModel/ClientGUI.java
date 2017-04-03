package clientServerModel;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements KeyListener,MouseListener{

	private static final long serialVersionUID = 1L;
	Button bt1,bt2,bt3,bt4,bt5,bt6;
	JTextField tf1,tf2, tf3;
	Box basebox, box1,box2,box3,box4;
	Box wholebox;
	JTextArea jta;
	
	private String hostname;
	private final static int port = 2680;
	private final static int Data_Size = 1024 * 60;
	private Socket clientSocket = null;
	DataOutputStream out = null;
	DataInputStream in = null;
	File localfile = null;
	String remotefile;
	FileOutputStream fos = null;
	long length=0;
	int flag = 0;
	
	public ClientGUI() throws HeadlessException {
		super();
		initial();
	}

	public ClientGUI(GraphicsConfiguration gc) {
		super(gc);
	}

	public ClientGUI(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public ClientGUI(String title) throws HeadlessException {
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
		tf1.setFont(new Font("TimesRoman",Font.ITALIC,15));
		tf1.addKeyListener(this);
		tf2=new JTextField("D:\\Hello.txt",30);
		tf2.setFont(new Font("TimesRoman",Font.ITALIC,15));
		tf2.addKeyListener(this);
		tf3=new JTextField("E:\\Hello.txt",30);
		tf3.setFont(new Font("TimesRoman",Font.ITALIC,15));
		tf3.addKeyListener(this);
		box2.add(tf1);
		box2.add(Box.createVerticalStrut(vertical));
		box2.add(tf2);
		box2.add(Box.createVerticalStrut(vertical));
		box2.add(tf3);
		
		box3=Box.createVerticalBox();
		bt1 = new Button("Connect");
		bt1.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt1.addMouseListener(this);
		bt2 = new Button("Verify");
		bt2.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt2.addMouseListener(this);
		bt3 = new Button("Download");
		bt3.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt3.addMouseListener(this);
		box3.add(bt1);
		box3.add(Box.createVerticalStrut(vertical));
		box3.add(bt2);
		box3.add(Box.createVerticalStrut(vertical));
		box3.add(bt3);
		
		box4=Box.createVerticalBox();
		bt4 = new Button("Disconnect");
		bt4.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt4.addMouseListener(this);
		bt5 = new Button("Clear");
		bt5.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt5.addMouseListener(this);
		bt6 = new Button("Clear");
		bt6.setFont(new Font("TimeRoman",Font.BOLD|Font.ITALIC,13));
		bt6.addMouseListener(this);
		box4.add(bt4);
		box4.add(Box.createVerticalStrut(vertical));
		box4.add(bt5);
		box4.add(Box.createVerticalStrut(vertical));
		box4.add(bt6);
		
		setBTEnabled();
		
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
		jta.setText(" Please type in the Server IP and try to connect first ......\n");
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
	
	public void keyTyped(KeyEvent e){
		JTextField jtf=(JTextField)e.getSource();
		if(e.getModifiers()==InputEvent.CTRL_MASK&&e.getKeyCode()==KeyEvent.VK_X){
			jtf.cut();
		}else if(e.getModifiers()==InputEvent.CTRL_MASK&&e.getKeyCode()==KeyEvent.VK_C){
			jtf.copy();
		}else if(e.getModifiers()==InputEvent.CTRL_MASK&&e.getKeyCode()==KeyEvent.VK_V){
			jtf.paste();
		}
	}
	public void keyPressed(KeyEvent e){
		
	}
	public void keyReleased(KeyEvent e){
		
	}
	
	public void mouseClicked(MouseEvent m) {
		Button bt=(Button)m.getSource();
		if(bt==this.bt1){
			if(tf1.getText()!=null){
				CreateConnection();
			}else{
				jta.append(" Warning: Please input a server ip first.\n");
			}
		}else if(bt==this.bt2){
			if(tf2.getText()!=null){
				transferFileName();
				try {
					length=in.readLong();
					if(length==0){
						System.out.println("The desired file doesn't exist!");
						System.out.println("Please re-input your desired file and verify......");
						jta.append(" The desired file doesn't exist!\n");
						jta.append(" Please re-input your desired file and verify......\n");
					}else{
						flag=2;
						setBTEnabled();
						System.out.println("Now you can choose your download path and saved filename .......");
						jta.append(" Now you can choose your download path and saved filename .......\n");
					}
				} catch (IOException e) {
					System.out.println("Error while receiving file size from server.");
					jta.append(" Error while receiving file size from server.\n");
					e.printStackTrace();
				}
			}else{
				System.out.println("Warning: Please input your desired file and path first!");
				jta.append(" Warning: Please input your desired file and path first!\n");
			}
		}else if(bt==this.bt3){
			boolean success=createLocalFile();
			if(success==true){
				receiveFile();
			}
		}else if(bt==this.bt4){
			tearDownConnection();
			
		}else if(bt==this.bt5){
			
		}else if(bt==this.bt6){
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setBTEnabled() {
		if (flag == 1) {
			bt1.setEnabled(false);
			bt2.setEnabled(true);
			bt3.setEnabled(false);
			bt4.setEnabled(true);
			bt5.setEnabled(true);
			bt6.setEnabled(true);
			tf1.setEditable(false);
			tf2.setEditable(true);
			tf3.setEnabled(true);
		} else if (flag == 0) {
			bt1.setEnabled(true);
			bt2.setEnabled(false);
			bt3.setEnabled(false);
			bt4.setEnabled(false);
			bt5.setEnabled(true);
			bt6.setEnabled(true);
			tf1.setEditable(true);
			tf2.setEditable(true);
			tf3.setEditable(true);
		}else if(flag==2){
			bt1.setEnabled(false);
			bt2.setEnabled(false);
			bt3.setEnabled(true);
			bt4.setEnabled(true);
			bt5.setEnabled(true);
			bt6.setEnabled(true);
			tf1.setEditable(false);
			tf2.setEditable(true);
			tf3.setEditable(true);			
		}
	}
	
	private void CreateConnection() {
		try {
			hostname=this.tf1.getText();
			clientSocket = new Socket(hostname, port);
			jta.append(" Connection established!\n");
			jta.append(" Now you can input your desired file and file path ......\n");
			System.out.println("Connection established!");
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream(), Data_Size));
			flag= 1;
			setBTEnabled();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connection failed! Please check your server ip and try to connect again.");
			jta.append(" Connection failed! Please check your server ip and try to connect again.\n");
			flag = 0;
			setBTEnabled();
		}
	}
	
	private void transferFileName(){
		try {
			remotefile=tf2.getText();
			out.writeUTF(remotefile);
		} catch (IOException e) {
			System.out.println(" Error while transfering filename which is to be downloaded!");
			jta.append(" Error while transfering filename which is to be downloaded!");
			e.printStackTrace();
		}
	}
	
	private boolean createLocalFile(){
		if(tf3.getText()!=null){
		localfile=new File(tf3.getText());
		if(localfile.isDirectory()){
			if(localfile.exists()){
				
			}else{
				
			}
		}else{			
			jta.append(" The download file path or filename is invalid. Please choose another ......\n");
			System.out.println("The download file path or filename is invalid. Please choose another ......");
			return false;
		}
		}else{
			localfile = new File(remotefile);
			String filename = localfile.getName();
			localfile = new File(filename);
			int count = 0;
			while (localfile.exists()) {
				localfile = new File(count + "_" + filename); 
				// If file already exists, rename it by a number.
				count++;
			}
			try {
				localfile.createNewFile();
				return true;
			} catch (IOException e) {
				System.err.println("Error while creating new file in localhost!");
				jta.append(" Error while creating new file in localhost!\n");
				e.printStackTrace();
				return false;
			}
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
			jta.append(" Error while closing socket!\n");
			e.printStackTrace();
		}finally{
			flag=1;
			setBTEnabled();
		}
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}
}
