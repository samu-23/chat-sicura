/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketAddress;
import java.net.Socket;

/**
 *
 * @author acer
 */
public class ClientRunnable implements Runnable {

    private Socket clientSocket;
    private String ipServer;
    private int portServer;
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    public ClientRunnable(String ip, int port) {
        ipServer = ip;
        portServer = port;
    }
    
    @Override
    public void run() {
        try {
            
            
            clientSocket = new Socket(ipServer, portServer);
            
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            
            System.out.println("CONNESSIONE APERTA");
            
        } catch (Exception ex) {
            System.out.println("CONNESSIONE FALLITA\n\n" + ex);
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
    
}
