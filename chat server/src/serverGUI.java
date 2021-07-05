import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class serverGUI {

	private JFrame frmServer;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverGUI window = new serverGUI();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public serverGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server ");
		frmServer.setBounds(100, 100, 450, 300);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JButton startserver = new JButton("Start");
		startserver.setBounds(75, 11, 89, 23);
		frmServer.getContentPane().add(startserver);
		
		JButton stopserver = new JButton("Stop");
		stopserver.setBounds(248, 11, 89, 23);
		frmServer.getContentPane().add(stopserver);
		
		textField = new JTextField();
		textField.setBounds(192, 45, 97, 20);
		frmServer.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnPortaServer = new JTextPane();
		txtpnPortaServer.setEditable(false);
		txtpnPortaServer.setText("Porta Server : ");
		txtpnPortaServer.setBounds(118, 45, 97, 20);
		frmServer.getContentPane().add(txtpnPortaServer);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 70, 414, 180);
		frmServer.getContentPane().add(textArea);
	}
}
