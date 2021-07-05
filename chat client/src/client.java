
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Function;


 

public class client {
	
	static ClientGUI w;
    
	static boolean stop = false;
	
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
    	do {
    		
    	
        try (Socket socket = new Socket(w.getIp(), Integer.parseInt(w.getPort()) )){
         
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            
            String userInput;
         

            ClientRunnable clientRun = new ClientRunnable(socket,(String message)->{return w.write(message);});


            new Thread(clientRun).start();
           
           do {
               
                    userInput = b.getString();
                    output.println(userInput);
                    if (userInput.equals("exit")) {
                        //reading the input from server
                    	stop = true;
                        break;
                }

           } while (!userInput.equals("exit"));
           
           

            
        } catch (Exception e) {
      
				System.err.println("errore");
		
        }
    }while(!stop);
    	
	}
}