package lab1;
public class BankTest {
    
    public static void main(String[] args) {
        
        BankAccount konto = new BankAccount("Ralf");
        konto.deposit(10000);
        System.out.print("Konto status\n" + konto.toString());
        System.out.println("Ta ut 500kr");
        konto.withdraw(500);        
        System.out.print("Konto status\n" + konto.toString());       
        
    }//end main
}//end class
