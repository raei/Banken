package lab1;

public class BankAccount {
    
    //instansvaribler
    private String ownerName; 
    private int balance;
    
    //konstruktor
    public BankAccount(String ownerName)
    {
        this.ownerName = ownerName;
        this.balance = 0;        
    }
    
    //metoder
    public boolean withdraw(int withdraw){
        boolean koll = false;
        if(withdraw <= this.balance){
            this.balance = this.balance - withdraw;
            koll = true;
        }   
        return koll;        
    }//end withdraw 
    
    
    public boolean deposit(int deposit){
        boolean koll = false;
        if(deposit > 0){
            this.balance = this.balance + deposit;
            koll = true;
        }
        return koll;
    }//end deposit
    
    public void setOwnerName(String name){
        this.ownerName = name;
    }//end setOwnerName
     
    public String getOwnerName(){
        return this.ownerName;
    }
    
    public int getBalance(){
        return  this.balance;
    }
    
    @Override
    public String toString(){
        return "Innehavare: " + this.ownerName + "\n" + 
                "Balance: " + this.balance + "\n";
    }//end toString
}//end class

