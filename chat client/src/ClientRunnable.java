import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;
import java.util.function.Function;

public class ClientRunnable implements Runnable {

    private Socket socket;
    private BufferedReader input;
    @SuppressWarnings("rawtypes")
	private Function a;

    public ClientRunnable(Socket s, Function<String,Integer> a ) throws IOException {
        this.socket = s;
        this.input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        this.a =a;
    }
    @SuppressWarnings("unchecked")
	@Override
    public void run() {
        
            try {
            	
            	
            	while(true) {
                    String response = input.readLine();
                    
                    a.apply(response);
                }
            } catch (IOException e) {
               a.apply("Disconnected");
               
            } finally {
                try {
                    input.close();
                } catch (Exception e) {
                    a.apply("Logout failed...(restart)");
                   
                    
                }
            }
    }
    
}