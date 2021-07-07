import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class server {
	
	static serverGUI w;
    static boolean stop = false;
	
	
	public static void main(String[] args) {
     
      serverGUI window = new serverGUI();
		
      w = window;		
		
		
		
		while(true) {	
			
		ArrayList<ServerThread> threadList = new ArrayList<>();
	    
	    try (ServerSocket serversocket = new ServerSocket(Integer.parseInt(w.getport()))){
	    	
	    	stop = false;
	    	
	    	w.write("server ready!");
	    	
	        while(!stop) {
	            Socket socket = serversocket.accept();

	        	w.write("Client connected");
	            ServerThread serverThread = new ServerThread(socket,(String message)->{return w.write(message);}, threadList);
	            //starting the thread
	            threadList.add(serverThread); 
	            serverThread.start();

	        }
	        for(ServerThread.activeCount();;) {
	        
	        w.write("Server closed");
	        }
	    } catch (Exception e) {
	    	w.write("Error");
	    	
	    }
	}
	
		
		
}
	
	public void stop() {
		
		stop = true;

	}
	
	

}			
