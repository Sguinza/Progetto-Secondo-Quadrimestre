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
public class MultiServerNascosto extends Thread implements Observer
{
    myRxTx myRxTx;
    
    
    public MultiServerNascosto()
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
                System.out.println("Sguinza ti ha mandato: " + messaggio);
                char carattere = messaggio.charAt(0);
                myRxTx.output.write(carattere);
                myRxTx.output.write('c');
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
            MultiServerNascosto server = new MultiServerNascosto();
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

