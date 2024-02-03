/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import java.net.*;
import java.io.*;

/**
 *
 * @author acer
 */
public class ServerHandler implements Runnable{
    private Socket socketClient;
    
    public ServerHandler(Socket socketClient){
        this.socketClient = socketClient;
    }
    
    public void run() {
        
        try{
        
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
            String dataClient = in.readLine();
            System.out.println("Client: " + dataClient);
       
            
            //socketClient.close();
        
        } catch (IOException e){
        
            e.printStackTrace();
        }
    
    
    
    
    
    }


}
