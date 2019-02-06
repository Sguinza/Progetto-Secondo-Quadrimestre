/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author boschini_federico
 * @brief Classe thread Server
 */
public class TCP_Server extends Thread
{
    /**
     * @brief Run del thread server
     */
    public void run()
    {
        try
        {
            MyTCP gestioneTCP = new MyTCP();
            
            ServerSocket welcomeSocket = new ServerSocket(3333); // socket di benvenuto
            System.out.println("SERVER AVVIATO IN ATTESA...");
            while(true)
            {
                gestioneTCP.MyTCPServer(welcomeSocket);
                String messaggio = gestioneTCP.ServerRicevi();
                System.out.println("Sguinza ti ha mandato: " + messaggio);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] argv)
    {
        TCP_Server server = new TCP_Server();
        server.start();
    }
    
}

