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
public class ServerHandler{

    private ServerSocket serverSocket;
    private int serverPort;
    
    public ServerHandler(int port) {
        serverPort = port;
    }
    
    public void openConnection() {
        
        //serverThread = new Thread(initializeThread());

        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (Exception ex) {
            
        }
    }
    
   
}
