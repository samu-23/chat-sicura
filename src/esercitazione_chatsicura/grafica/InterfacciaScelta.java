/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura.grafica;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  

/**
 *
 * @author Tommaso Melani
 */
public class InterfacciaScelta extends JFrame implements ActionListener {

    private Container container = this.getContentPane();
    
    private JPanel northPanel = new JPanel();
    private JLabel titleNorthPanel = new JLabel("Chat Sicura");
    
    private JPanel centralPanel = new JPanel();
    private JButton clientButton = new JButton("Client");
    private JButton serverButton = new JButton("Server");
    
    public InterfacciaScelta(String title) {
        setTitle(title);
        
        // Impostazioni pannelli e componenti
        northPanel.setBackground(Color.LIGHT_GRAY);
        titleNorthPanel.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        clientButton.addActionListener(this);
        serverButton.addActionListener(this);
        
        // Aggiunta pannelli e componenti
        container.add(northPanel, BorderLayout.NORTH);
        container.add(centralPanel, BorderLayout.CENTER);
        northPanel.add(titleNorthPanel);
        
        centralPanel.add(clientButton);
        centralPanel.add(Box.createRigidArea(new Dimension(20,0)));
        centralPanel.add(serverButton);
        
        setBounds(100,100,400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clientButton) {
            InterfacciaClient ic = new InterfacciaClient("Client");
            setVisible(false);
        }
        if (e.getSource() == serverButton) {
            InterfacciaServer is = new InterfacciaServer("Server");
            setVisible(false);
        }
    }
    
}
