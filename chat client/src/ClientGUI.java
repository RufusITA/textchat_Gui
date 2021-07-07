
import javax.swing.JFrame;
import javax.swing.JTextField;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;




public class ClientGUI {

	String in = "";
	
	String name = "";
	
	private buffer buffo;
	private JTextArea schermo;
	private JFrame frmChatTextprealpha;
	private JTextField input;
	private JTextField Nick;
	private JTextField ip1;
	private JTextField porta1;
	private boolean run = false;

	
	public ClientGUI() {
		
		try {
			UIManager.setLookAndFeel(
			    UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {

		}
		
		initialize();
		
		frmChatTextprealpha.setVisible(true);
		
	}


	private void initialize() {
		
		
		buffo = new buffer();
		
		
		frmChatTextprealpha = new JFrame();
		frmChatTextprealpha.setTitle("Client (pre-alpha V.0.2)");
		frmChatTextprealpha.setResizable(false);
		frmChatTextprealpha.setBounds(100, 100, 750, 480);
		frmChatTextprealpha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatTextprealpha.getContentPane().setLayout(null);
		
		input = new JTextField();
		input.setFont(new Font("Arial", Font.PLAIN, 11));
		input.setBounds(87, 390, 519, 20);
		frmChatTextprealpha.getContentPane().add(input);
		input.setColumns(10);
	
		
		
		Nick = new JTextField();
		Nick.setFont(new Font("Arial", Font.PLAIN, 11));
		Nick.setBounds(87, 11, 261, 20);
		frmChatTextprealpha.getContentPane().add(Nick);
		Nick.setColumns(10);
		
		JButton send = new JButton("SEND");
		send.setBounds(631, 389, 89, 23);
		frmChatTextprealpha.getContentPane().add(send);
		
		ip1 = new JTextField();
		ip1.setFont(new Font("Arial", Font.PLAIN, 11));
		ip1.setBounds(87, 42, 261, 20);
		frmChatTextprealpha.getContentPane().add(ip1);
		ip1.setColumns(10);
		
		porta1 = new JTextField();
		porta1.setText("7777");
		porta1.setFont(new Font("Arial", Font.PLAIN, 11));
		porta1.setBounds(87, 73, 261, 20);
		frmChatTextprealpha.getContentPane().add(porta1);
		porta1.setColumns(10);
		
		JButton nickbot = new JButton("Set");
		nickbot.setBounds(358, 11, 72, 20);
		frmChatTextprealpha.getContentPane().add(nickbot);
		
		JButton bconnect = new JButton("Connect");
		bconnect.setBounds(631, 105, 89, 23);
		frmChatTextprealpha.getContentPane().add(bconnect);
		
		JLabel lblNewLabel = new JLabel("UserName : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(16, 11, 89, 20);
		frmChatTextprealpha.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Server IP : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(16, 42, 72, 20);
		frmChatTextprealpha.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Server Port : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(16, 73, 72, 20);
		frmChatTextprealpha.getContentPane().add(lblNewLabel_2);
		
		JTextArea nickdisplay = new JTextArea();
		nickdisplay.setFont(new Font("Arial Black", Font.PLAIN, 12));
		nickdisplay.setEditable(false);
		nickdisplay.setBounds(587, 8, 133, 22);
		frmChatTextprealpha.getContentPane().add(nickdisplay);
		
		JLabel lblNewLabel_3 = new JLabel("username set :");
		lblNewLabel_3.setBounds(490, 10, 116, 20);
		frmChatTextprealpha.getContentPane().add(lblNewLabel_3);
		
		JButton stopconnection = new JButton("Disconnect");
		stopconnection.setBounds(631, 139, 89, 23);
		frmChatTextprealpha.getContentPane().add(stopconnection);
		
		JLabel lblNewLabel_4 = new JLabel("pre - alpha V.0.2");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(641, 423, 93, 17);
		frmChatTextprealpha.getContentPane().add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(87, 104, 519, 275);
		frmChatTextprealpha.getContentPane().add(scrollPane);
		
		schermo = new JTextArea();
		schermo.setDragEnabled(true);
		schermo.setFocusable(false);
		scrollPane.setViewportView(schermo);
		schermo.setEditable(false);
		
		
		nickbot.addActionListener(e->{
			
			name = Nick.getText();
			
			nickdisplay.setText(name);
			
		});
		
		Nick.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					nickbot.doClick();
				}
			}
		});
		
		bconnect.addActionListener(e->{
			if(run) {
				buffo.add("exit");
				
				synchronized(this){notifyAll();}
			}
			else {
			run=true;
			
			synchronized(this){notifyAll();}
			}
		});
		
		stopconnection.addActionListener(e->{
			
			run = false;
			
			buffo.add("exit");
			
		});
		
		
		send.addActionListener(e->{
			
			String in2 = input.getText();
			
			in ="[" + name + "]  " + in2;
			
			buffo.add(in + "\n");
			
			input.setText("");
			
		});
		
		input.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					send.doClick();
				}
			}
		});
		
	}
	
	public buffer getbuffer (){
		return buffo;
	}
	 
	public synchronized String getIp (){
		
		while(true){
			if(!run)
				try {
					wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			else
				return ip1.getText();
		}

	}
	public synchronized String getPort (){
		while(true){
			if(!run)
				try {
					wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			else
				return porta1.getText();
		}
		
	}
	
	public int write(String s){
		schermo.append(s + "\n");
		return 0;
	}
	
public int connesso(boolean connected) {
		
		run = connected;
		
		return 0;
	}
}