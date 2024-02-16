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
public class InterfacciaDebugServer extends JFrame implements ActionListener {

    private Container container = this.getContentPane();
    
    private JPanel northPanel = new JPanel();
    private JLabel titleLabel = new JLabel("Debug Server");
    
    private JPanel centralPanel = new JPanel();
    private JLabel aLabel = new JLabel("a:");
    private JTextField aTF = new JTextField();
    private JLabel gLabel = new JLabel("g:");
    private JTextField gTF = new JTextField();
    private JLabel pLabel = new JLabel("p:");
    private JTextField pTF = new JTextField();
    private JLabel BLabel = new JLabel("B: ");
    private JTextField BTF = new JTextField();
    private JLabel KeyLabel = new JLabel("Key: ");
    private JTextField KeyTF = new JTextField();
    
    private int[] params;
    
    public InterfacciaDebugServer(String title) {
        
        setResizable(false);
        setTitle(title);
        setBounds(200, 0, 300, 150);
        setVisible(true);
        
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        
        northPanel.setBackground(Color.LIGHT_GRAY);
        
        aTF.setEditable(false);
        gTF.setEditable(false);
        pTF.setEditable(false);
        BTF.setEditable(false);
        KeyTF.setEditable(false);
        
        aTF.setPreferredSize(new Dimension(30,20));
        gTF.setPreferredSize(new Dimension(30,20));
        pTF.setPreferredSize(new Dimension(30,20));
        BTF.setPreferredSize(new Dimension(30,20));
        KeyTF.setPreferredSize(new Dimension(30,20));
        
        northPanel.add(titleLabel);
        centralPanel.add(aLabel);
        centralPanel.add(aTF);
        centralPanel.add(gLabel);
        centralPanel.add(gTF);
        centralPanel.add(pLabel);
        centralPanel.add(pTF);
        centralPanel.add(BLabel);
        centralPanel.add(BTF);
        centralPanel.add(KeyLabel);
        centralPanel.add(KeyTF);
        
        container.add(northPanel, BorderLayout.NORTH);
        container.add(centralPanel, BorderLayout.CENTER);
        
    }
    
    public void setParams(int[] params) {
        this.params = params;
        // 1 - Numero primo, 2 - Generatore, 3 - A, 4 - b
        aTF.setText("" + params[0]);
        gTF.setText("" + params[1]);
        pTF.setText("" + params[2]);
        BTF.setText("" + params[3]);
        KeyTF.setText("" + params[4]);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
    }
    
}
