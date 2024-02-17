/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import esercitazione_chatsicura.grafica.InterfacciaClient;
import esercitazione_chatsicura.grafica.InterfacciaServer;
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
    
    private InterfacciaClient clientInterf;
    
    private String ipServer;
    private int portServer;
    private final JTextField clientTextField;
    private JButton clientSendButton;
    private JTextArea clientInOutArea;
    
    private int count = 0;
    
    private DiffieHellman DH;
    private int B;
    
    private int a;
    private int p;
    private int key;
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    public ClientRunnable(String ip, int port, JTextField textField, JButton sendButton, JTextArea inOutArea, InterfacciaClient clientInterf) {
        ipServer = ip;
        portServer = port;
        clientTextField = textField;
        clientSendButton = sendButton;
        clientInOutArea = inOutArea;
        this.clientInterf = clientInterf;
        
        DH = new DiffieHellman(new File(System.getProperty("user.home") + "/Desktop/primeNumbers.txt"));
        
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
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            
            JOptionPane.showMessageDialog(null, "Connessione stabilita!\n");
            
            byte[] clientBuffer = new byte[2048];
            int bytesRead;

            int[] parms = DH.clientValues();
            
            System.out.println(parms.toString());
            
            dataOutputStream.writeInt(parms[0]);
            dataOutputStream.flush();
            dataOutputStream.writeInt(parms[1]);
            dataOutputStream.flush();
            dataOutputStream.writeInt(parms[2]);
            dataOutputStream.flush();

            a = parms[3];
            p = parms[0];
            
            int[] allValues = new int[6];
            allValues[0] = parms[0]; // p
            allValues[1] = parms[1]; // g
            allValues[2] = parms[2]; // A
            allValues[3] = parms[3]; // a
             
            B = dataInputStream.readInt();
            key = (int) Math.pow(B, a) % p;
            
            allValues[4] = B;
            allValues[5] = key; // key
            
            if (clientInterf.idc != null) clientInterf.idc.setParams(allValues);
            
            while ((bytesRead = inputStream.read(clientBuffer)) != 1) {

                
                String clientMessage =  new String(clientBuffer, 0, bytesRead);
                String decryptedMessage = CrittografiaCesare.decrittaMessaggio(clientMessage, key);
                clientInOutArea.append("\nServer: " + decryptedMessage + "| Encrypted: " + clientMessage);
                break;
                
                 
            }
            
        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                JOptionPane.showMessageDialog(null, "File primeNumbers.txt non trovato!\nConnessione interrotta!", "ERRORE: File non trovato", JOptionPane.ERROR_MESSAGE);
                try {
                    clientSocket.close();
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
            System.out.println(encryptedMessage);
            if (message.length() != 0) {
                outputStream.write(encryptedMessage.getBytes());
                clientInOutArea.append("\nClient: " + message + "| Encrypted: " + encryptedMessage);
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
