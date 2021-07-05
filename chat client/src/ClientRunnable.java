import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;
import java.util.function.Function;

public class ClientRunnable implements Runnable {

    private Socket socket;
    private BufferedReader input;
    private Function a;

    public ClientRunnable(Socket s, Function<String,Integer> a ) throws IOException {
        this.socket = s;
        this.input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        this.a =a;
    }
    @Override
    public void run() {
        
            try {
            	
            	a.apply("Connesso");
            	while(true) {
                    String response = input.readLine();
                    
                    a.apply(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    
}