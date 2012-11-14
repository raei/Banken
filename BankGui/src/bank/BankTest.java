/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author Ralf Eriksson
 */
public class BankTest {
    
    public static void main(String[] args) {
        
        BankAccount konto = new BankAccount("Ralf");
        konto.deposit(10000);
        System.out.print("Konto status\n" + konto.toString());
        
    }
    
}
