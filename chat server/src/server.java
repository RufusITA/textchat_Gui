

public class server {
	
	static serverGUI w;
    
	String porta;
    
	public static void main(String[] args) {
      
		serverGUI window = new serverGUI();
		
		w = window;		
		
		serverone server = new serverone();
		
		server.start((String message)->{return w.write(message);},w.getport());
		
		
}
}			
