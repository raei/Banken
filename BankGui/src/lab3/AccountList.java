package lab3;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AccountList {
    
    private static ArrayList<BankAccount> kontoLista = new ArrayList<>();
    
    public static void addAccountToList(BankAccount konto){
        kontoLista.add(konto);
    }   
    
    public static BankAccount searchAccountInList(String namn){
         int index = 0;
         boolean found = false;//boolean to end while loop
         BankAccount hittatAccount = null;
         while(index < kontoLista.size() && !found)
         {
            BankAccount tempAccount = kontoLista.get(index);
            String accountOwner = tempAccount.getOwnerName();
            if(accountOwner.equalsIgnoreCase(namn))
            {
                hittatAccount = tempAccount;
                found = true;
            }else{
                index++;
            }
        }       
        return hittatAccount;        
    }//end  searchAccountInList  
    
    public static void removeAccountFromList(BankAccount konto){
        if(konto != null){
            kontoLista.remove(konto);                 
        }else{
            JOptionPane.showMessageDialog(null, "Det gick inte att ta bort kontot");                    
        }
    }
}//end class
