/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import java.net.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author acer
 */
public class ServerRunnable implements Runnable {

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private int portServer;
    private final JTextField serverTextField;
    private JButton serverSendButton;
    private JTextArea serverInOutArea;
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    
    public ServerRunnable(int port, JTextField textField, JButton sendButton, JTextArea inOutArea) {
        portServer = port;
        serverTextField = textField;
        serverSendButton = sendButton;
        serverInOutArea = inOutArea;
        
        serverSendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == serverSendButton) sendMessage(textField.getText());
            }
        });
        
    }
    
    @Override
    public void run() {
        try {
            
            serverSocket = new ServerSocket(portServer);
            
            System.out.println("CONNESSIONE APERTA");
            
            clientSocket = serverSocket.accept();
            
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            
            byte[] serverBuffer = new byte[2048];
            int bytesRead;

            while ((bytesRead = inputStream.read(serverBuffer)) != 1) {
                String clientMessage =  new String(serverBuffer, 0, bytesRead);
                serverInOutArea.append("\n" + clientMessage);
            }
            
        } catch (Exception ex) {
            System.out.println("CONNESSIONE FALLITA\n\n" + ex);
        }
        
        
    }
    
    public void sendMessage(String message) {
        try {
            String newMessage = "Server: " + message;
            if (message.length() != 0) {
                outputStream.write(newMessage.getBytes());
                serverInOutArea.append("\n" + newMessage);
                outputStream.flush();
                System.out.println("MESSAGGIO INVIATO");
            } else {
                System.out.println("MESSAGGIO VUOTO");
            }
            
        } catch (IOException ex) {
            System.out.println("MESSAGGIO FALLITO AD INVIARE " + ex);
        }
        
    }
    
}
