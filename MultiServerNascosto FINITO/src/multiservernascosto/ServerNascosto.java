/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package multiservernascosto;

import java.net.ServerSocket;
import java.util.Observer;


/**
 *
 * @author boschini_federico
 * @brief Classe thread Server
 */
public class ServerNascosto implements Observer
{   
    /**
     * @brief Run del thread server
     */
    public void run()
    {
        try
        {
            GestioneTCP gestioneTCP = new GestioneTCP();
            GestioneTCP gestioneClient = new GestioneTCP();
            gestioneClient.MyTCPClient(10002);
            
            ServerSocket welcomeSocket = new ServerSocket(3333); // socket di benvenuto
            System.out.println("SERVER AVVIATO IN ATTESA...");
            while(true)
            {
                gestioneTCP.MyTCPServer(welcomeSocket);
                String messaggio = gestioneTCP.ServerRicevi();
                String[] parts = messaggio.split(",");
                System.out.println("Messaggio ricevuto: " + messaggio);
                String comando = parts[0].toUpperCase();
                
                if(comando.equals("MODALITA"))
                {
                    char c = parts[1].charAt(0);
                    System.out.println("valore parsato: " + c);
                    messaggio = Character.toString(c);
                    gestioneClient.ClientManda(messaggio);
                }
                if(comando.equals("MUOVI"))
                {
                    char c = parts[1].charAt(0);
                    System.out.println("valore parsato: " + c);
                    messaggio = Character.toString(c);
                    Thread.sleep(20);
                    gestioneClient.ClientManda(messaggio);
                }
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] argv)
    {
        try
        {
            ServerNascosto server = new ServerNascosto();
            server.run();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void update(java.util.Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

