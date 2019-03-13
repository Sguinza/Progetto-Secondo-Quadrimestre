/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multiservernascosto;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boschini_federico
 * @brief Classe thread Server
 */
public class ServerNascosto extends Thread implements Observer
{
    myRxTx myRxTx;
    
    
    public ServerNascosto()
    {
        Informazioni info = new Informazioni();
        myRxTx = new myRxTx(info);
        myRxTx.initialize();
        info.addObserver(this);
    }

    /**
     * @brief Run del thread server
     */
    public void run()
    {
        try
        {
            GestioneTCP gestioneTCP = new GestioneTCP();
            
            ServerSocket welcomeSocket = new ServerSocket(3333); // socket di benvenuto
            System.out.println("SERVER AVVIATO IN ATTESA...");
            while(true)
            {
                gestioneTCP.MyTCPServer(welcomeSocket);
                String messaggio = gestioneTCP.ServerRicevi();
                String[] parts = messaggio.split(",");
                System.out.println("Messaggio ricevuto: " + messaggio);
                
                if(parts[0].equals("MUOVI") || parts[0].equals("muovi"))
                {
                    char c = parts[1].charAt(0);
                    System.out.println("valore partsato: " + c);
                    myRxTx.output.write(c);
                    myRxTx.output.write('\n');
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
            server.start();
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

