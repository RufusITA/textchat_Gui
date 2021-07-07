
import java.io.PrintWriter;
import java.net.Socket;



 

public class client {
	
	static ClientGUI w;
    
	static boolean stop = false;
	
	public static void main(String[] args) {
  
					ClientGUI window = new ClientGUI();
					w = window;
		
    	while(true) {	 
    		
		buffer b = w.getbuffer();
		
        try (Socket socket = new Socket(w.getIp(), Integer.parseInt(w.getPort()))){
        	
        	w.write("Connected");
        	
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            
            String userInput;
         

            ClientRunnable clientRun = new ClientRunnable(socket,(String message)->{return w.write(message);});


            new Thread(clientRun).start();
           
           do {
       
                    userInput = b.getString();
                    
                    if (userInput.equals("exit")) {

                        break;
                }
                
                output.println(userInput);

           } while (!userInput.equals("exit"));

            
        } catch (Exception e) {
        		 
        		w.connesso(false);
				w.write("not connected successfully");
		
        }
        
        if(stop) {
        	w.connesso(false);
        }
    }
    	
	
}
	}