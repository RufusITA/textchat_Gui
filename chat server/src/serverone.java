import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Function;

public class serverone {
	
	static serverGUI w;
	public void start(Function<String,Integer> a , String porta) {
		
	ArrayList<ServerThread> threadList = new ArrayList<>();
    
    try (ServerSocket serversocket = new ServerSocket(Integer.parseInt(porta))){
    	
    	a.apply("server pronto!");
    	System.out.print("all on");
        while(true) {
            Socket socket = serversocket.accept();

        	a.apply("Client connesso");
            ServerThread serverThread = new ServerThread(socket, threadList);
            //starting the thread
            threadList.add(serverThread); 
            serverThread.start();


        }
    } catch (Exception e) {
    	a.apply("Errore");
    	e.printStackTrace();
    }
}
}
