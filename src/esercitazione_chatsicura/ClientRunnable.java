/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author acer
 */
public class ClientRunnable implements Runnable {

    private Socket clientSocket;
    
    private String ipServer;
    private int portServer;
    private final JTextField clientTextField;
    private JButton clientSendButton;
    private JTextArea clientInOutArea;
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    public ClientRunnable(String ip, int port, JTextField textField, JButton sendButton, JTextArea inOutArea) {
        ipServer = ip;
        portServer = port;
        clientTextField = textField;
        clientSendButton = sendButton;
        clientInOutArea = inOutArea;
        
        clientSendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == clientSendButton) sendMessage(textField.getText());
            }
        });
        
    }
    
    @Override
    public void run() {
        try {
            
            
            clientSocket = new Socket(ipServer, portServer);
            
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            
            System.out.println("CONNESSIONE APERTA");
            
            byte[] clientBuffer = new byte[2048];
            int bytesRead;

            while ((bytesRead = inputStream.read(clientBuffer)) != 1) {
                String clientMessage =  new String(clientBuffer, 0, bytesRead);
                clientInOutArea.append("\n" + clientMessage);
            }
            
        } catch (Exception ex) {
            System.out.println("CONNESSIONE FALLITA\n\n" + ex);
        }
        
        
    }
    
    public void sendMessage(String message) {
        try {
            String newMessage = "Client: " + message;
            if (message.length() != 0) {
                outputStream.write(newMessage.getBytes());
                clientInOutArea.append("\n" + newMessage);
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
