package javaprovadb;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * @author boschini_federico 
 * @brief Classe per la gestione del database
 */
public class GestioneDB 
{

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    /**
     * @author boschini_federico 
     * @brief Costruttore di default che inizializza la connessione
     */
    public GestioneDB() 
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "");
            stmt = con.createStatement();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * @author boschini_federico 
     * @brief Metodo per ottenere tutti gli utenti
     * @return tabella
     */
    public ResultSet GetUtenti() 
    {
        try 
        {
            ResultSet rs = stmt.executeQuery("select * from utenti");
            return rs;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @author boschini_federico 
     * @brief Metodo per permettere la registrazione di un utente nel database
     * @return esito operazione
     */
    public boolean Registra(String username, String password) 
    {
        boolean esito = false;
        boolean puoiFarlo = true;
        try 
        {
            if (username.equals("") || password.equals("")) 
            {
                return false;
            }
            else 
            {
                ResultSet rs = GetUtenti();
                while (rs.next()) 
                {
                    if (rs.getString(2).equals(username) && rs.getString(3).equals(password)) 
                    {
                        puoiFarlo = false;
                        return puoiFarlo;
                    } 
                    else 
                    {
                        puoiFarlo = true;
                    }
                }

                if (puoiFarlo) 
                {
                    stmt.executeUpdate("insert into `utenti` (idUtente, username, password) VALUES (NULL" + ",'" + username + "','" + password + "');");
                    esito = true;
                    return esito;
                }
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            esito = false;
            return esito;
        }
        return false;
    }

    /**
     * @author boschini_federico 
     * @brief Metodo per permettere la login dell'utente nel database
     * @param username username dell'utente
     * @param password password dell'utente
     * @return esito dell'operazione
     */
    public boolean Login(String username, String password) 
    {
        boolean esito = false;
        try 
        {

            if (username.equals("") || password.equals("")) 
            {
                return false;
            } 
            else 
            {
                ResultSet rs = stmt.executeQuery("select * from utenti where(username like '" + username + "' and password like '" + password + "')");

                while (rs.next()) 
                {
                    if (rs.getString(2).equals(username) && rs.getString(3).equals(password)) 
                    {
                        esito = true;
                        return esito;
                    }
                }
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            esito = false;
            return esito;
        }
        return false;
    }

}