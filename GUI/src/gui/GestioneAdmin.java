/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author boschini_federico
 */
public class GestioneAdmin 
{
    private Statement stmt;
    private Connection con;
    
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
    
    
    
    public boolean rimuoviUtente(Utente utente)
    {
        try
        { 
            System.out.println("username: " + utente.getUsername());
            System.out.println("mail: " + utente.getEmail());
            //stmt.executeUpdate("delete from utenti where(username = '" + "pollo00ilgozzo" + "' and email = '" + "polloni_matteo@ismonnet.eu" + "');");
            //con.prepareStatement("delete from utenti where(username = '" + utente.getUsername() + "' and email = '" + utente.getEmail() + "');");
            // pollo00ilgozzo polloni_matteo@ismonnet.eu
            //PreparedStatement st = con.prepareStatement("delete from utenti where(username = '" + "pollo00ilgozzo" + "' and email = '" + "polloni_matteo@ismonnet.eu" + "');");
            //st.setString(1,name);
            PreparedStatement st = con.prepareStatement("delete from utenti where(username = '" + utente.getUsername() + "' and email = '" + utente.getEmail() + "');");
            st.executeUpdate(); 



            con.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
    }
    
    
}
