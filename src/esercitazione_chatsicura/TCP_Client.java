/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;


import java.io.*;
import java.net.*;

public class TCP_Client {
    public static void main(String[] args)throws IOException{
    
        try{
            Socket socket = new Socket("localhost",9999);
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("Connessione riuscita");
            
            String serverResponse = in.readLine();
            System.out.println("Risposta server" + serverResponse);
            
            socket.close();
            
        } catch(IOException e){
        
            e.printStackTrace();
        }

    }
}
