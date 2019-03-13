/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author boschini_federico
 */
public class GestioneAdmin 
{
    private gestioneDB gestioneDb;
    
    public GestioneAdmin()
    {
        try
        {
            //inizializzare connessione
            //javaconnector=nome database
            //root=admin db,""=pass dell admin deafault
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto_secondo_quadrimestre", "root", ""); 
            Statement stmt = con.createStatement();
            gestioneDb = new gestioneDB();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public boolean inserisciUtente()
    {
        return true;
    }
    
    
    
    public boolean rimuoviUtente()
    {
        return true;
    }
    
    
}
