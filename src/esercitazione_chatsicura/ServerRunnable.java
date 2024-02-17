/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import esercitazione_chatsicura.grafica.InterfacciaServer;
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

    private InterfacciaServer serverIntrf;
    
    private int portServer;
    private final JTextField serverTextField;
    private JButton serverSendButton;
    private JTextArea serverInOutArea;
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    private DiffieHellman DH;
    private int p;
    private int g;
    private int A;
    private int B;
    
    private int key;
    
    public ServerRunnable(int port, JTextField textField, JButton sendButton, JTextArea inOutArea, InterfacciaServer serverIntrf) {
        portServer = port;
        serverTextField = textField;
        serverSendButton = sendButton;
        serverInOutArea = inOutArea;
        this.serverIntrf = serverIntrf;
        
        DH = new DiffieHellman(new File(System.getProperty("user.home") + "/Desktop/primeNumbers.txt"));
        
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
            
            JOptionPane.showMessageDialog(null, "Connessione stabilita!\n");
            
            clientSocket = serverSocket.accept();
            
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            
            byte[] serverBuffer = new byte[2048];
            int bytesRead;
            
            p = dataInputStream.readInt();
            g = dataInputStream.readInt();
            A = dataInputStream.readInt();
            int[] values = DH.serverValues(A, g, p);

            B = values[0];
            dataOutputStream.writeInt(B);
            int b = values[1];
            key = (int) Math.pow(A, b) % p;
            
            int[] allValues = new int[6];
            allValues[0] = p; // p
            allValues[1] = g; // g
            allValues[2] = A; // A
            allValues[3] = B; // B
            allValues[4] = b; // b
            allValues[5] = key;
            
            if (serverIntrf.idb != null) serverIntrf.idb.setParams(allValues);
            
            while ((bytesRead = inputStream.read(serverBuffer)) != 1) {

                String clientMessage =  new String(serverBuffer, 0, bytesRead);
                String decryptedMessage = CrittografiaCesare.decrittaMessaggio(clientMessage, key);
                serverInOutArea.append("\nClient: " + decryptedMessage + "| Encrypted: " + clientMessage);
               
            }
            
        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                JOptionPane.showMessageDialog(null, "File primeNumbers.txt non trovato!\nConnessione interrotta!", "ERRORE: File non trovato", JOptionPane.ERROR_MESSAGE);
                try {
                    serverSocket.close();
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Connessione interrotta:\n" + ex);
            }
            
        }
        
        
    }
    
    public void sendMessage(String message) {
        try {
            String encryptedMessage = CrittografiaCesare.crittaMessaggio(message, key);
            if (message.length() != 0) {
                outputStream.write(encryptedMessage.getBytes());
                serverInOutArea.append("\nServer: " + message + "| Encrypted: " + encryptedMessage);
                outputStream.flush();
                JOptionPane.showMessageDialog(null, "Messaggio inviato");
            } else {
                JOptionPane.showMessageDialog(null, "Il messaggio inserito è vuoto!");
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Message has not been sent:\n\n" + ex);
        }
        
    }
    
}
