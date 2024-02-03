/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_chatsicura;

import java.io.*;
import java.util.*;
/**
 *
 * @author acer
 */
public class DiffieHellman {
    
    private File primeNumbers;
    private List<String> primeNumbersList;
    private int chiaveA;
    private int chiaveB;
    
    public DiffieHellman(File primeNumbersFile) {
        primeNumbers = primeNumbersFile;
        primeNumbersList = loadFile();
    }
    
    private List<String> loadFile() {
        List<String> lines = new ArrayList<>();
        int lineNow = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(primeNumbers.getAbsolutePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNow++;
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Couldn't load line " + lineNow +" into List<String>:\n" + e.getMessage());
        }

        return lines;
    }
    
    public int clientValues() {
        
        /*
        * p - Pubblico
        * g - Pubblico
        * a - Privato
        * KA - Privato
        */
        
        Random randomManager = new Random();
        
        int randomPrimeNumber = randomManager.nextInt(0, primeNumbersList.size());
        int p = Integer.parseInt(primeNumbersList.get(randomPrimeNumber));
        
        int g = randomManager.nextInt(2, randomPrimeNumber - 1);
        int a = randomManager.nextInt(2, randomPrimeNumber - 1);
        
        int KA = (int) (Math.pow(g, a) % p);
        
        return KA;
        
    }
    
    public int serverValues() {
        
        /*
        * p - Pubblico
        * g - Pubblico
        * b - Privato
        * KA - Privato
        */
        
        Random randomManager = new Random();
        
        int randomPrimeNumber = randomManager.nextInt(0, primeNumbersList.size());
        int p = Integer.parseInt(primeNumbersList.get(randomPrimeNumber));
        
        int g = randomManager.nextInt(2, randomPrimeNumber - 1);
        int b = randomManager.nextInt(2, randomPrimeNumber - 1);
        
        int KB = (int) (Math.pow(g, b) % p);
        
        return KB;
        
    }
    
    public int getChiaveA() {
        return chiaveA;
    }
    
    public int getChiaveB() {
        return chiaveB;
    }
    
}
