/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author jedda_ibrahim
 */
public class gestioneDB
{

        public boolean aggiungiUtente(String username,String password,String email)
    {
        boolean condizione=true;
        
            try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto_secondo_quadrimestre", "root", ""); //javaconnector=nome database
                                                                                                                    //root=admin db,""=pass dell admin deafault

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select username from utenti where username='"+username+"'");

            while (rs.next())
            {
               if(rs.getString(1).equals(username))
               {
                   condizione=false;
                   System.out.println("UTENTE GIA' PRESENTE!");
               }
         
            }

            con.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        
        if(condizione==true)
        {
            
     
            try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto_secondo_quadrimestre", "root", ""); //javaconnector=nome database
                                                                                                                    //root=admin db,""=pass dell admin deafault

            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `utenti`(`username`, `password`, `email`) VALUES " + "(" + "'" + username + "'" + "," + "'" + password + "','"+email+"'"+")");
            System.out.println("INSERITO CON SUCCESSO!");
            

            con.close();

        } 
        
        catch (Exception e)
        {
            System.out.println(e);
        }
            
        // fine inserimento sql
    
            try{
            String host ="smtp.gmail.com" ;
            String user = "pattolluniprovamail@gmail.com";
            String pass = "cicciograziani123";
            String to = email;
            String from = "pattolluniprovamail@gmail.com";
            String subject = "Mail di conferma della registrazione";
            String messageText ="Gentile utente le tue informazioni sono username: "+username+ " password: "+password ;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("mail inviata con successo");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
            
             }
       
        
        return condizione;
    
        
    }
        
    
    public boolean login(String username,String password)
    {
        boolean risultato=false;
        
         
        
            try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto_secondo_quadrimestre", "root", ""); //javaconnector=nome database
                                                                                                                    //root=admin db,""=pass dell admin deafault

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select username,password from utenti where username='"+username+"' and password='"+password+"'");

            while (rs.next())
            {
               if(rs.getString(1).equals(username)&& rs.getString(2).equals(password))
               {
                   risultato=true;
                   System.out.println("LOGGATO CON SUCCESSO!");
               }
               else
               {
                  
               }
            }

            con.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        
        
        
        return risultato;       
    }
    
    /**
     * @author boschini_federico
     * @brief
     * @return 
     */
    public List<Utente> getUtenti()
    {  
        List lista = new ArrayList();
        Utente utente = new Utente();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto_secondo_quadrimestre", "root", ""); //javaconnector=nome database                                                                                                //root=admin db,""=pass dell admin deafault
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from utenti");
            while(rs.next())
            {
                if(!rs.getString(2).equals("admin") && !rs.getString(2).equals("boschini") && !rs.getString(2).equals("jedda"))
                {
                    utente = new Utente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    lista.add(utente);
                }

            }
            con.close();
            return lista;

        } catch (Exception e)
        {
            System.out.println(e);
        }
        
        return lista;
        
    }
        
        
        
    //fine classe
}
