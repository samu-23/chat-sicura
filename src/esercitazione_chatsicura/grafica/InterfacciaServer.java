/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura.grafica;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  
import java.net.*;
import java.io.*;

/**
 *
 * @author acer
 */
public class InterfacciaServer extends JFrame implements ActionListener {
    
    private Container container = this.getContentPane();
    
    private JPanel ipPanel = new JPanel();
    private JButton ipGetButton = new JButton("Vedi IP");
    private JLabel ipLabel = new JLabel("IP:");
    private JTextField ipTextField = new JTextField();
    private JLabel portLabel = new JLabel("Porta:");
    private JTextField portTextField = new JTextField();
    private JButton openConnectionButton = new JButton("Apri connessione");
    
    public String ipAddress;
    
    public InterfacciaServer(String title) {
        
        setTitle(title);
        setBounds(200, 0, 700, 700);
        
        // Impostazioni pannelli e componenti
        ipPanel.setBackground(Color.LIGHT_GRAY);
        
        ipTextField.setEditable(false);
        ipTextField.setPreferredSize(new Dimension(100,25));
        portTextField.setPreferredSize(new Dimension(100,25));
        
        ipGetButton.addActionListener(this);
        openConnectionButton.addActionListener(this);
        
        // Aggiunta pannelli e componenti
        
        ipPanel.add(ipGetButton);
        ipPanel.add(Box.createRigidArea(new Dimension(20,0)));
        ipPanel.add(ipLabel);
        ipPanel.add(ipTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(100,0)));
        ipPanel.add(portLabel);
        ipPanel.add(portTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(20,0)));
        ipPanel.add(openConnectionButton);
        
        container.add(ipPanel, BorderLayout.NORTH);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ipGetButton) {
             
            try {
                ipAddress = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            ipTextField.setText(ipAddress);
        }
        
        if (e.getSource() == openConnectionButton) {
            System.out.println("CONNESSIONE APERTA");
            // Aprire connessioni
        }
        
    }
    
}
