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
public class ServerRunnable implements Runnable {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    public ServerRunnable(ServerSocket socket) {
        serverSocket = socket;
    }
    
    @Override
    public void run() {
        try {
            clientSocket = serverSocket.accept();
            
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            
            byte[] serverBuffer = new byte[2048];
            int bytesRead;

            while ((bytesRead = inputStream.read(serverBuffer)) != 1) {
                String clientMessage =  new String(serverBuffer, 0, bytesRead);
                print("Client: " + clientMessage);
                
                String serverMessage = "Server: ok";
                outputStream.write(serverMessage.getBytes());
                outputStream.flush();
            }
            
        } catch (Exception ex) {
            
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    private void print(String message) {
        System.out.println(message);
    }
    
}
