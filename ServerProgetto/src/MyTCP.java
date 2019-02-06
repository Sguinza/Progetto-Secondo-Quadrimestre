
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author boschini_federico
 * @brief Classe per l'utilizzo del protocollo TCP
 */
public class MyTCP {

    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private BufferedReader inFromServer;
    private DataOutputStream outToServer;
    private Socket connectionSocket;
    private Socket clientSocket;

    /**
     * Costruttore di default
     */
    public MyTCP() {

    }

    /**
     * @brief Procedura con parametri del client
     * @param socket Socket del client
     */
    public void MyTCPClient(int socket) {
        try {
            clientSocket = new Socket("localhost", socket); //crea socket client
            outToServer = new DataOutputStream(clientSocket.getOutputStream()); //Crea output
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //Crea input per server
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @brief Funzione con parametri del server
     * @param welcomeSocket WelcomeSocket del server
     * @return Restituisce il socket
     */
    public Socket MyTCPServer(ServerSocket welcomeSocket) {

        try {
            connectionSocket = welcomeSocket.accept(); // prende socket del client
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); // prepara l'input
            outToClient = new DataOutputStream(connectionSocket.getOutputStream()); // prepara l'output
            return connectionSocket;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * @brief Metodo che permette al client di mandare al server un messaggio
     * @param messaggio Messaggio da spedire
     */
    public void ClientManda(String messaggio) {
        try {
            outToServer.writeBytes(messaggio + "\n");
            //outToServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @brief Metodo che permette al client di ricevere un messaggio dal server
     * @return Restituisce il messaggio
     */
    public String ClientRicevi() {
        String messaggio = "";
        try {
            messaggio = inFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messaggio;

    }

    /**
     * @brief Metodo che permette al server di mandare un messaggio al client
     * @param messaggio Messaggio da spedire
     */
    public void ServerManda(String messaggio) {
        try {
            outToClient.writeBytes(messaggio + "\n");
            outToClient.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @brief Metodo che permette al server di ricevere un messaggio dal client
     * @return Restituisce il messaggio
     */
    public String ServerRicevi() {
        String messaggio = "";
        try {
            messaggio = inFromClient.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return messaggio;

    }

    /**
     * @brief Metodo che chiude il socket del client
     */
    public void ChiudiSocketClient() {
        try {
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
