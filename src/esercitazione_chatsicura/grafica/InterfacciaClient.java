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
public class InterfacciaClient extends JFrame implements ActionListener {

    private Container container = this.getContentPane();
    
    private JPanel ipPanel = new JPanel();
    private JLabel ipLabel = new JLabel("IP:");
    private JTextField ipTextField = new JTextField();
    private JLabel portLabel = new JLabel("Porta:");
    private JTextField portTextField = new JTextField();
    private JButton connectButton = new JButton("Connettiti");
    
    public InterfacciaClient(String title) {
        
        setTitle(title);
        setBounds(200, 0, 700, 700);
        
        // Impostazioni pannelli e componenti
        ipPanel.setBackground(Color.LIGHT_GRAY);
        
        ipTextField.setPreferredSize(new Dimension(100,25));
        portTextField.setPreferredSize(new Dimension(100,25));
        
        connectButton.addActionListener(this);
        
        // Aggiunta pannelli e componenti
        
        ipPanel.add(ipLabel);
        ipPanel.add(ipTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(100,0)));
        ipPanel.add(portLabel);
        ipPanel.add(portTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(20,0)));
        ipPanel.add(connectButton);
        
        container.add(ipPanel, BorderLayout.NORTH);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == connectButton) {
            // Connettiti alla socket Server
        }
        
    }
    
}
