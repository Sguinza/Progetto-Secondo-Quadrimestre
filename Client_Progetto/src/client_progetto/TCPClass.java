/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package client_progetto;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author maspes_marco
 *
 * @brief Classe descrittiva dell'utilizzo delle principali funzioni di comunicazione
 * tramite protocollo tcp
 */
public class TCPClass {
    private BufferedReader br = null;
    private Socket socket;
    private DataOutputStream osw;
    private InputStreamReader isr;
    
    /**
     * @brief Costruttore della classe se utilizzata dal server
     * @param socket server di benvenuto che permette al server di instanziare una nuova comunicazione
     * su un socket dedicato
     */
    public TCPClass(ServerSocket socket)
    {
        try{
            this.socket = socket.accept();
            isr = new InputStreamReader(this.socket.getInputStream());
            osw = new DataOutputStream(this.socket.getOutputStream());
            br = new BufferedReader(isr);
            
            this.socket.setSoTimeout(30000);
        }catch(IOException ex){System.out.println(ex.getMessage());}
        
        
    }
    
    /**
     * @ Costruttore della classe se utilizzato da un client
     * @param ip indirizzo ip del server
     * @param port porta su cui bisogna collegarsi per poter scambiare dati con il server
     */
    public TCPClass(InetAddress ip, int port)
    {
        try
        {
            socket = new Socket(ip, port);
            
            socket.setSoTimeout(30000);
            isr = new InputStreamReader(this.socket.getInputStream());
            osw = new DataOutputStream(socket.getOutputStream());
            br = new BufferedReader(isr);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * @brief metodo che permette di leggere le informazioni inviate dal colloquiante sulla
     * parte opposta del socket
     * @return restituisce una stringa contenente tutte le informazioni fino al fine riga
     */
    public String waitToRead()
    {
        
        String message = "";
        
        try{           
            message = br.readLine();
            
        }catch(IOException ex){System.out.println(ex.getMessage());}
        
        return message;
    }
    
    /**
     * @brief metodo che permette di inviare informazioni al colloquiante sulla parte opposta del socket
     * @param message Stringa contenente il messaggio da inviare
     */
    public void sendTo(String message)
    {
        try
        {
            osw.writeBytes(message);
            osw.flush();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * @brief metodo che permette la chiusura del buffer di scrittura
     */
    public void closeScrittura()
    {
        try{
            osw.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * @brief metodo che permette di chiudere il socket
     */
    public void closeSocket()
    {
        try{
            socket.close();
        }catch(IOException ex){System.out.println(ex.getMessage());}
    }
    
}

