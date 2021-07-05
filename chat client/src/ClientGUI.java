
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;




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
		frmChatTextprealpha.setTitle("Client (pre-alpha V.0.12)");
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
		
		
		JTextPane txtpnPrealphaV = new JTextPane();
		txtpnPrealphaV.setForeground(Color.RED);
		txtpnPrealphaV.setBackground(Color.WHITE);
		txtpnPrealphaV.setEditable(false);
		txtpnPrealphaV.setText("pre-alpha V.0.12");
		txtpnPrealphaV.setBounds(587, 11, 133, 20);
		frmChatTextprealpha.getContentPane().add(txtpnPrealphaV);
		
		JButton send = new JButton("INVIA");
		send.setBounds(631, 389, 89, 23);
		frmChatTextprealpha.getContentPane().add(send);
		
		ip1 = new JTextField();
		ip1.setFont(new Font("Arial", Font.PLAIN, 11));
		ip1.setBounds(87, 42, 261, 20);
		frmChatTextprealpha.getContentPane().add(ip1);
		ip1.setColumns(10);
		
		porta1 = new JTextField();
		porta1.setFont(new Font("Arial", Font.PLAIN, 11));
		porta1.setBounds(87, 73, 261, 20);
		frmChatTextprealpha.getContentPane().add(porta1);
		porta1.setColumns(10);
		
		JButton nickbot = new JButton("assegna");
		nickbot.setBounds(358, 11, 86, 20);
		frmChatTextprealpha.getContentPane().add(nickbot);
		
		JButton bconnect = new JButton("Connetti");
		bconnect.setBounds(482, 72, 89, 23);
		frmChatTextprealpha.getContentPane().add(bconnect);
		
		JLabel lblNewLabel = new JLabel("NickName : ");
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
		nickdisplay.setBounds(587, 40, 133, 22);
		frmChatTextprealpha.getContentPane().add(nickdisplay);
		
		JLabel lblNewLabel_3 = new JLabel("NickName settato : ");
		lblNewLabel_3.setBounds(471, 42, 116, 20);
		frmChatTextprealpha.getContentPane().add(lblNewLabel_3);
		
		schermo = new JTextArea();
		schermo.setEditable(false);
		schermo.setBounds(87, 104, 519, 275);
		frmChatTextprealpha.getContentPane().add(schermo);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(610, 104, 17, 274);
		frmChatTextprealpha.getContentPane().add(scrollBar);
		
		JButton stopconnection = new JButton("Disconnetti");
		stopconnection.setBounds(587, 73, 89, 23);
		frmChatTextprealpha.getContentPane().add(stopconnection);
		
		
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
			
			run = true;
			synchronized(this){notifyAll();}

		});
		
		stopconnection.addActionListener(e->{
			
			buffo.add("exit \n");
			
			schermo.append("Disconnesso");
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
}