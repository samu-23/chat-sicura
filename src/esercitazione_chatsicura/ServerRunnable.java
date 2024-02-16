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
    
    private int count = 1;
    
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
            
            System.out.println("CONNESSIONE APERTA");
            
            clientSocket = serverSocket.accept();
            
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            
            byte[] serverBuffer = new byte[2048];
            int bytesRead;

            System.out.println(count);
            
            p = dataInputStream.readInt();
            g = dataInputStream.readInt();
            A = dataInputStream.readInt();
            int[] values = DH.serverValues(A, g, p);

            B = values[0];
            dataOutputStream.writeInt(B);
            int b = values[1];
            key = (int) Math.pow(A, b) % p;
            
            int[] allValues = new int[5];
            allValues[0] = p;
            allValues[1] = g;
            allValues[2] = A;
            allValues[3] = b;
            allValues[4] = key;
            
            if (serverIntrf.idb != null) serverIntrf.idb.setParams(allValues);
            
            while ((bytesRead = inputStream.read(serverBuffer)) != 1) {

                String clientMessage =  new String(serverBuffer, 0, bytesRead);
                String decryptedMessage = CrittografiaCesare.decrittaMessaggio(clientMessage, key);
                serverInOutArea.append("\n" + decryptedMessage);
               
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Connessione interrotta:\n" + ex);
        }
        
        
    }
    
    public void sendMessage(String message) {
        try {
            String encryptedMessage = CrittografiaCesare.crittaMessaggio(message, key);
            if (message.length() != 0) {
                outputStream.write(encryptedMessage.getBytes());
                serverInOutArea.append("\n" + message);
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
