import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class server {
	
	
	static serverGUI w;
    static boolean stop = false;
	private ServerSocket ss;
	
	private ArrayList<ServerThread> threadList;
	
	
	public static void main(String[] args) {
     
      serverGUI window = new serverGUI();
      
     server ser = new server(window);
     window.connetto(ser);
     
     ser.esecuzione();
}

	
	public int stop() {
		
		stop = true;
		try {
		synchronized (ss) {
			ss.close();
		}	
		
		threadList.forEach((ServerThread s)->{
			
			try {
				s.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		});
		
		
		} catch (IOException e) {
			
			w.write("some error");
		}
		
		
		return 0;
	}
	
	
	
	public server(serverGUI window) {
		
		 w = window;
		
	}
	
	public void esecuzione() {
		
		while(true) {	
			
			
			 threadList = new ArrayList<>();
		    
		    try {
		    	
		    	ss = new ServerSocket(Integer.parseInt(w.getport()));
		    	
		    	stop = false;
		    	
		    	w.write("server ready!");
		    	
		        while(!stop) {

		            Socket socket = ss.accept();

		        	w.write("Client connected");
		            ServerThread serverThread = new ServerThread(socket,(String message)->{return w.write(message);}, threadList);     
		            //starting the thread
		            threadList.add(serverThread); 
		            serverThread.start();
		        }
		     
		    } catch (Exception e) {
		    	w.write("Server Closed!");
		    	
		    	
		    }
		}
	}
	

	
}			
