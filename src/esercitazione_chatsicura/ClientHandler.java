/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author acer
 */
public class ClientHandler implements Runnable{
    private Socket socketClient;
    
    public ClientHandler(Socket socketClient){
        this.socketClient = socketClient;
    }
    
    public void run() {
        
        try{
        
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
            String dataClient = in.readLine();
            System.out.println("Dati ricevuti dal client" + dataClient);
        
            out.println("Dati ricevuti correttamente");
            
            socketClient.close();
        
        } catch (IOException e){
        
            e.printStackTrace();
        }
    
    
    
    
    
    }


}
