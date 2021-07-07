import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class serverGUI {

	static server ser;
	
	
	private JFrame frmServer;
	private JTextField porta;
	private JTextArea log;
	
	private boolean run = false;
	private JScrollPane scrollPane;
	
	public serverGUI() {
		
		initialize();
		
		frmServer.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmServer = new JFrame();
		frmServer.setResizable(false);
		frmServer.setTitle("Server pre-alpha V.0.2");
		frmServer.setBounds(100, 100, 510, 326);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JButton startserver = new JButton("Start");
		startserver.setBounds(125, 11, 89, 23);
		frmServer.getContentPane().add(startserver);
		
		JButton stopserver = new JButton("Stop");
		stopserver.setBounds(275, 11, 89, 23);
		frmServer.getContentPane().add(stopserver);
		
		porta = new JTextField();
		porta.setText("7777");
		porta.setBounds(223, 45, 97, 20);
		frmServer.getContentPane().add(porta);
		porta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Port : ");
		lblNewLabel.setBounds(175, 45, 63, 20);
		frmServer.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 474, 210);
		frmServer.getContentPane().add(scrollPane);
		
		 log = new JTextArea();
		 scrollPane.setViewportView(log);
		 log.setEditable(false);
		
		
startserver.addActionListener(e->{
	
	if(!run) {
	log.append("inizialization server.... \n");
	
	run = true;
	synchronized(this){notifyAll();}
	}
});


stopserver.addActionListener(e ->{
	
	if(run) {
		
		ser.stop();
		
		run = false;
		
	}
	
});
	}

	public synchronized String getport() {
	
		while(true){
			if(!run)
				try {
					wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			else
				
				return porta.getText();
		}
	
}
	
	public int write(String s){
		log.append(s + "\n");
		return 0;
	}
	
	
}