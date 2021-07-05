import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {
	
	static serverGUI w;
	
		
	public static void main(String[] args) {
      
		serverGUI window = new serverGUI();
		
		w = window;		
				
			
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket serversocket = new ServerSocket(/*Integer.parseInt(w.getPort())*/5000)){
        	
        	System.out.println("server pronto!");
            while(true) {
                Socket socket = serversocket.accept();

            	System.out.println("Client connesso");
                ServerThread serverThread = new ServerThread(socket, threadList);
                //starting the thread
                threadList.add(serverThread); 
                serverThread.start();


            }
        } catch (Exception e) {
            System.err.println("Errore");
        }
    }
}