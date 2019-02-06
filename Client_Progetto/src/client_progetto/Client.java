/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_progetto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

/**
 *
 * @author guindani_christian
 */
public class Client {
    
  BufferedReader input;                      // stream di input 
  TCPClass tcpclass;
  
  String percorso,ricevuto;
  
  public void comunica() {
    
        try{
            System.out.println("connesso");
            ricevuto = input.readLine();
            } catch (IOException ex)
            {System.out.println(ex.getMessage());}
    
            tcpclass.sendTo(ricevuto + "\r\n");
            

    
                  
    tcpclass.closeScrittura();
                tcpclass.closeSocket();   
  }
  
  public void connetti(){
      
    try 
    {
            tcpclass = new TCPClass(InetAddress.getByName("172.16.102.106"), 3333);
            
            input= new BufferedReader(new InputStreamReader(System.in));
            
            ricevuto = percorso = "";
    }  
    catch (Exception e) 
   {
      System.out.println(e.getMessage());
      System.out.println("Errore durante la connessione!");
      System.exit(1);
   }
 }
  
 public static void main(String args[]) {
     Client cliente = new Client();
     cliente.connetti();
     cliente.comunica();
  }   
}


