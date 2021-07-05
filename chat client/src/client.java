import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

 

public class client {
	
	static ClientGUI w;
    
	public static void main(String[] args) {
    	
    	/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {*/
					ClientGUI window = new ClientGUI();
					w = window;
			
			/*	} catch (Exception e) {
					System.err.print("errore grafico");
				}
			}
		});*/
    	
		buffer b = w.getbuffer();
    	
        try (Socket socket = new Socket(w.getIp(), Integer.parseInt(w.getPort()) )){
         
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            
            
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            
            String userInput;
            String response;
            String clientName = "empty";

            ClientRunnable clientRun = new ClientRunnable(socket,(String message)->{return w.write(message);});


            new Thread(clientRun).start();
           
           do {
               
                    userInput = b.getString();
                    output.println(userInput);
                    if (userInput.equals("exit")) {
                        //reading the input from server
                        break;
                }

           } while (!userInput.equals("exit"));
           


            
        } catch (Exception e) {
            System.err.println("Errore  " );
            
            e.printStackTrace();
    }
    }
}