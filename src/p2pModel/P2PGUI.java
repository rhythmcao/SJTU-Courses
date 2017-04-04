package p2pModel;
import java.awt.AWTEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class P2PGUI extends JFrame implements KeyListener,MouseListener{

	private static final long serialVersionUID = 1L;
	Button bt1,bt2,bt3,bt4,bt5,bt6;
	JTextField tf1,tf2, tf3;
	Box basebox, box1,box2,box3,box4;
	Box wholebox;
	JTextArea jta,jts;
	
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
	
	public P2PGUI() throws HeadlessException {
		super();
		initial();
	}

	public P2PGUI(GraphicsConfiguration gc) {
		super(gc);
	}

	public P2PGUI(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public P2PGUI(String title) throws HeadlessException {
		super(title);
	}

	public void initial() {
		setTitle("File Sharing--Client");
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
		
		jta=new JTextArea(12,40);
		jta.setEditable(false);
		jta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
		jta.setFont(new Font("TimeRoman",Font.PLAIN,15));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setText(" Please type in the Server IP and try to connect first ......\n");
		JScrollPane jsp1=new JScrollPane(jta);
		jts=new JTextArea(8,40);
		jts.setEditable(false);
		jts.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
		jts.setFont(new Font("TimeRoman",Font.PLAIN,15));
		jts.setLineWrap(true);
		jts.setWrapStyleWord(true);
		jts.setText(" Server side:\n");
		JScrollPane jsp2=new JScrollPane(jts);
		wholebox=Box.createVerticalBox();
		wholebox.add(basebox);
		wholebox.add(Box.createVerticalStrut(vertical));
		wholebox.add(jsp1);
		wholebox.add(Box.createVerticalStrut(vertical));
		wholebox.add(jsp2);
		
		Container con=getContentPane();
		con.setLayout(new FlowLayout());
		con.add(wholebox);
		con.validate();
		pack();
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
			CreateConnection();
		}else if(bt==this.bt2){
			transferFileName();
		}else if(bt==this.bt3){
			boolean success=createLocalFile();
			if(success==true){
				receiveFile();
			}
		}else if(bt==this.bt4){
			tearDownConnection();
		}else if(bt==this.bt5){
			tf2.setText("");
		}else if(bt==this.bt6){
			tf3.setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
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
		if(!tf1.getText().equals("")){
			try {
				hostname=this.tf1.getText();
				clientSocket = new Socket(hostname, port);
				jta.append(" Connection established!\n");
				jta.append(" Now you can input your desired file and file path ......\n");
				out = new DataOutputStream(clientSocket.getOutputStream());
				in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream(), Data_Size));
				flag= 1;
				setBTEnabled();
			} catch (IOException e) {
				jta.append(" Connection failed! Please check your server ip and try to connect again.\n");
			}
		}else{
			jta.append(" Warning: Please input a server ip first.\n");
		}
	}
	
	private void transferFileName(){
		if(!tf2.getText().equals("")){
			remotefile=tf2.getText();
			try {
				out.writeUTF(remotefile);
				try {
					length = in.readLong();
					if (length == 0) {
						jta.append(" The desired file doesn't exist!\n");
						jta.append(" Please re-input your desired file and verify......\n");
					} else {
						flag = 2;
						setBTEnabled();
						jta.append(" Now you can choose your download path and filename to be saved .......\n");
					}
				} catch (IOException e) {
					jta.append(" Error while receiving file size from server.\n");
				}
			} catch (IOException e) {
				jta.append(" Error while transfering filename which is to be downloaded!");
			}
		}else{
			jta.append(" Warning: Please input your desired file and path first!\n");
		}
	}
	
	private boolean createLocalFile() {
		if (!tf3.getText().equals("")) {
			localfile = new File(tf3.getText());
			try {
				if (localfile.isDirectory()) {
					File rtf = new File(remotefile);
					String filename = rtf.getName();
					String path=localfile.getAbsolutePath();
					localfile = new File(path + "\\"+filename);
					int count = 0;
					while (localfile.exists()) {
						localfile = new File(path + "\\(" + count+")"+filename);
						// If file already exists, rename it by a number.
						count++;
					}
					System.out.println("Line319");
					System.out.println(localfile.getAbsolutePath());
					localfile.createNewFile();
					if(localfile.isFile())
						return true;
					else throw new IOException();
				} else {
					localfile.createNewFile();
					if(localfile.isFile())
						return true;
					else throw new IOException();
				}
			} catch (IOException e) {
				jta.append(" The download file path or filename is invalid. Please choose another ......\n");
				return false;
			}
		} else {
			jta.append(" Please input a file path or filename to be saved first ......\n");
			return false;
		}
	}
	
	private void receiveFile(){
		try {
				jta.append(" Size of the desired file is " + length+".\n");
				jta.append(" File receiving started!\n");
				fos = new FileOutputStream(localfile);
				int size = 0;
				int current = 0;
				byte[] bs = new byte[Data_Size];
				while ((size = in.read(bs)) != -1) {
					fos.write(bs, 0, size);
					bs = new byte[Data_Size];
					current += size;
					if (current >= length) {
						jta.append(" File receiving completed!\n");
						jta.append(" File has been saved to " + localfile.getAbsolutePath() + ".\n");
						break;
					}
				}
				if (fos != null)
					fos.close();
		} catch (IOException e) {
			jta.append(" Error while receiving desired file!\n");
		}finally{
			flag=1;
			setBTEnabled();
			jta.append(" Input another file you want to download from the server.\n");
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
			jta.append(" Connection has been disconnected!\n");
		} catch (Exception e) {
			jta.append(" Error while closing socket!\n");
		}finally{
			flag=0;
			setBTEnabled();
		}
	}
	
	public static void main(String[] args) {
		
		P2PGUI p2pclient=new P2PGUI();
		
		//Press Esc to exit the program
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.addAWTEventListener(new AWTEventListener(){
                public void eventDispatched(AWTEvent e){
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        KeyEvent evt = (KeyEvent) e;
                        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            p2pclient.dispose();
                            System.exit(0);
                        }
                    }
                }
            },AWTEvent.KEY_EVENT_MASK);
        
     // Create an anoymous thread acting as a server
     		new Thread() {
     			public void run() {
     				try {
     					@SuppressWarnings("resource")
     					ServerSocket server = new ServerSocket();
     					InetSocketAddress address = new InetSocketAddress("localhost", port);
     					server.bind(address);
     					while (true) {
     						Socket connection = server.accept();
     						p2pclient.jts.append(" Client "+connection.getInetAddress()+" is connected ......\n");
     						Thread task = new P2PGUI.p2pFileThread(connection,p2pclient.jts);
     						task.start();
     					}
     				} catch (IOException ex) {
     					p2pclient.jts.append(" Port " + port + " is occupied!\n");
     				}
     			}
     		}.start();
	}
	private static class p2pFileThread extends Thread {
		private Socket connection = null;
		private JTextArea msg=null;
		public p2pFileThread(Socket connection,JTextArea msg) {
			this.connection = connection;
			this.msg=msg;
		}

		public void run() {
			try {
				DataOutputStream ps = new DataOutputStream(connection.getOutputStream());
				DataInputStream commands=new DataInputStream(connection.getInputStream());
				while (true) {
					String file = commands.readUTF();
					msg.append(" Client "+connection.getInetAddress()+" request "+file+".\n");
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
						msg.append(" File "+filePath.getName()+" transferring to Client "+connection.getInetAddress()+" completed!\n");
						if(fis!=null)
							fis.close();
					}
					else{
						ps.writeLong(0);
						msg.append(" No such file. Nothing to be transfered to "+connection.getInetAddress()+".\n");
					}	
				}
			} catch (IOException e) {
				msg.append(" One client terminates its connection!\n");
			}
		}
	}
}
