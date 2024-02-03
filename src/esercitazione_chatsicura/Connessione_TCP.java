/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 
 * @author Utente
 */

package esercitazione_chatsicura;

import java.net.*;
import java.io.*;



public class Connessione_TCP {
    public static void main(String[] args) throws IOException {
    try{
        ServerSocket serverSocket = new ServerSocket(5001);
        
        System.out.println("In attesa del client");
            while(true){
        Socket socketClient =  serverSocket.accept();
        System.out.println("Connessione accettata");
        String clientIP = socketClient.getInetAddress().toString();
        int portaClient = socketClient.getPort();
        System.out.println("{IP " + clientIP + ",Porta: " + portaClient + "] " + "Connessione riuscita");
   
        Thread clientThread = new Thread(new ClientHandler(socketClient));
        clientThread.start();
        
            }
         
    } catch (IOException e){
       e.printStackTrace(); 
     }
    
   }
    
}
