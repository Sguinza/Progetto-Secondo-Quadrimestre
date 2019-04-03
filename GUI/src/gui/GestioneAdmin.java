/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author boschini_federico
 * @brief Classe per la gestione delle operazioni degli admin
 */
public class GestioneAdmin 
{
    private Statement stmt;
    private Connection con;
    
    /**
     * @author boschini_federico
     * @brief Costruttore con parametri che instaura la connessione con il db
     */
    public GestioneAdmin()
    {
        try
        {
            //inizializzare connessione
            //javaconnector=nome database
            //root=admin db,""=pass dell admin deafault
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto_secondo_quadrimestre", "root", ""); 
            stmt = con.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   
    /**
     * @author boschini_federico
     * @brief Metodo che permette all'admin di aggiugere un utente
     * @param username username dell'utente
     * @param password password dell'utente
     * @param email email dell'utente
     * @return esito booleano
     */
    public boolean AggiungiUtente(String username, String password, String email)
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
    
    /**
     * @author boschini_federico
     * @brief Metodo che permette all'admin di rimuovere un utente selezionato
     * @param utente oggetto utente selezionato
     * @return esito booleano
     */
    public boolean rimuoviUtente(Utente utente)
    {
        try
        { 
            System.out.println("username: " + utente.getUsername());
            System.out.println("mail: " + utente.getEmail());
            PreparedStatement st = con.prepareStatement("delete from utenti where(username = '" + utente.getUsername() + "' and email = '" + utente.getEmail() + "');");
            st.executeUpdate(); 

            //con.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
}
