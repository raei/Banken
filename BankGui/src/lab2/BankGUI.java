package lab2;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class BankGUI extends JFrame{
    
    private JTabbedPane tabPane;
    private AddAccount addAccountPanel;
    private SearchAccount searchAccountPanel;
    
    
    public BankGUI() {        
        tabPane = new JTabbedPane();
        addAccountPanel = new AddAccount();
        searchAccountPanel = new SearchAccount();        
        tabPane.add("Add account",addAccountPanel);
        tabPane.add("Search account", searchAccountPanel);        
        this.add(tabPane);       
    }//end konstruktor
    
    public static void main(String[] args) {
        BankGUI frame = new BankGUI();
        frame.setSize(800, 600);
        frame.setTitle("Handelsbanken");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(BankGUI.EXIT_ON_CLOSE);
        frame.setVisible(true);       
    }//end main    
}//end class
