import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class serverGUI {

	private static final String String = null;
	private JFrame frmServer;
	private JTextField porta;

	private boolean run = false;
	
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
		frmServer.setTitle("Server ");
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
		porta.setBounds(223, 45, 97, 20);
		frmServer.getContentPane().add(porta);
		porta.setColumns(10);
		
		JTextArea log = new JTextArea();
		log.setEditable(false);
		log.setBounds(10, 70, 474, 211);
		frmServer.getContentPane().add(log);
		
		JLabel lblNewLabel = new JLabel("Porta : ");
		lblNewLabel.setBounds(175, 45, 63, 20);
		frmServer.getContentPane().add(lblNewLabel);
		
		
startserver.addActionListener(e->{
	
	if(!run) {
	log.append("inizializzazione server.... \n");
	
	run = true;
	synchronized(this){notifyAll();}
	}
});


stopserver.addActionListener(e ->{
	
	if(run) {
		
		log.append("Server stoppato \n");
		
		run = false;
		
		
	}

	
	
	
});


public String getPort(){
	while(true){
		if(!run)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		else {
			return porta.gettext();
	}
	
}
}



}
}