/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura.grafica;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Tommaso Melani
 */
public class InterfacciaDebugClient extends JFrame implements ActionListener {

    private Container container = this.getContentPane();
    
    private JPanel northPanel = new JPanel();
    private JLabel titleLabel = new JLabel("Debug Client");
    
    private JPanel centralPanel = new JPanel();
    private JLabel bLabel = new JLabel("b:");
    private JTextField bTF = new JTextField();
    private JLabel gLabel = new JLabel("g:");
    private JTextField gTF = new JTextField();
    private JLabel pLabel = new JLabel("p:");
    private JTextField pTF = new JTextField();
    private JLabel ALabel = new JLabel("A: ");
    private JTextField ATF = new JTextField();
    private JLabel KeyLabel = new JLabel("Key: ");
    private JTextField KeyTF = new JTextField();
    
    private int[] params;
    
    public InterfacciaDebugClient(String title) {
        
        setResizable(false);
        setTitle(title);
        setBounds(200, 0, 300, 150);
        setVisible(true);
        
        bTF.setEditable(false);
        gTF.setEditable(false);
        pTF.setEditable(false);
        ATF.setEditable(false);
        KeyTF.setEditable(false);
        
        bTF.setPreferredSize(new Dimension(30,20));
        gTF.setPreferredSize(new Dimension(30,20));
        pTF.setPreferredSize(new Dimension(30,20));
        ATF.setPreferredSize(new Dimension(30,20));
        KeyTF.setPreferredSize(new Dimension(30,20));
        
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        
        northPanel.setBackground(Color.LIGHT_GRAY);
        centralPanel.add(bLabel);
        centralPanel.add(bTF);
        centralPanel.add(gLabel);
        centralPanel.add(gTF);
        centralPanel.add(pLabel);
        centralPanel.add(pTF);
        centralPanel.add(ALabel);
        centralPanel.add(ATF);
        centralPanel.add(KeyLabel);
        centralPanel.add(KeyTF);
        
        northPanel.add(titleLabel);
        
        container.add(northPanel, BorderLayout.NORTH);
        container.add(centralPanel, BorderLayout.CENTER);
        
        
    }
    
    public void setParams(int[] params) {
        this.params = params;
        // 1 - Numero primo, 2 - Generatore, 3 - A, 4 - a
        bTF.setText("" + params[0]);
        gTF.setText("" + params[1]);
        pTF.setText("" + params[2]);
        ATF.setText("" + params[3]);
        KeyTF.setText("" + params[4]);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
    }
    
}
