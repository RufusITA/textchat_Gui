import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Function;


public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    private Function<String, ?> a;
    
    public ServerThread(Socket socket,Function<String,Integer> a, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
        this.a = a;
        
    }

    @Override
    public void run() {
        try {
            
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            
             output = new PrintWriter(socket.getOutputStream(),true);

             printToALlClients("a user connected");
             
            //inifite loop for server
            while(true) {
                String outputString = input.readLine();
                a.apply(outputString);
                //if user types exit command
                if(outputString.equals("exit")) {
                    break;
                }
                else {
                printToALlClients( outputString );
                }
            }


        } catch (Exception e) {
        	
        	printToALlClients("a user has logged out");
        	
        	a.apply("Client Disconnected");
        	
        	
        	
        }
    }

    private void printToALlClients(String outputString) {
        for( ServerThread sT: threadList) {
            sT.output.println(outputString);
        }

    }
}