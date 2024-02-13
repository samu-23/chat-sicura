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
    private int primeNumber;
    private int a;
    
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
        primeNumber = Integer.parseInt(primeNumbersList.get(randomPrimeNumber));
        
        int g = randomManager.nextInt(2, randomPrimeNumber - 1);
        a = randomManager.nextInt(2, randomPrimeNumber - 1);
        
        int KA = (int) (Math.pow(g, a) % primeNumber);
        
        return KA;
        
    }
    
    public int serverValues(int KA) {
        
        /*
        * p - Pubblico
        * b - Privato
        * KA - Privato
        * KB - Pubblico
        */
        
        Random randomManager = new Random();
        
        int randomPrimeNumber = randomManager.nextInt(0, primeNumbersList.size());
        int p = Integer.parseInt(primeNumbersList.get(randomPrimeNumber));
        
        int b = randomManager.nextInt(2, randomPrimeNumber - 1);
        
        int KB = (int) (Math.pow(KA, b) % p);
        
        return KB;
        
    }
    
    public int clientEndValues(int KB) {
        return (int) (Math.pow(KB, a) % primeNumber); 
    }
    
}
