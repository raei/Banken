package lab5;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class BankGUI extends JFrame{
    
    private JTabbedPane tabPane;
    private AddAccount addAccountPanel;
    private SearchAccount searchAccountPanel;
    private Login login;
    
    
    public BankGUI() {  
        login = new Login();
        tabPane = new JTabbedPane();
        addAccountPanel = new AddAccount();
        searchAccountPanel = new SearchAccount();        
        tabPane.add("Add account",addAccountPanel);
        tabPane.add("Search account", searchAccountPanel);        
        
        final int nTabIndex = tabPane.indexOfTabComponent( tabPane );
        final boolean bIsVisible = tabPane.isEnabledAt( 1);
        if ( bIsVisible== false ) {
            // Do stuff with myPanel
            this.repaint();
        }
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
