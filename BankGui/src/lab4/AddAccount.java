package lab4;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AddAccount extends JPanel implements ActionListener{
    
    private JPanel layoutPanel;
    private JLabel lbUserName, lbBalance;
    private JTextField txfUserName, txfBalance;
    private JButton btnAdd;
    private Border loweredbevel;
    private static BankAccount konto;
    
    public AddAccount(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        layoutPanel = new JPanel();
        layoutPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 5, 2);       
        lbUserName = new JLabel("Username:");
        lbBalance = new JLabel("Balance:");
        txfUserName = new JTextField(15);
        txfBalance = new JTextField(15);
        btnAdd = new JButton("Add");  
        btnAdd.addActionListener(this);
        
        gbc.anchor = GridBagConstraints.EAST;//Flyttar  all objekt till höger
        gbc.gridx = 0;
        gbc.gridy = 0;
        layoutPanel.add(lbUserName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        layoutPanel.add(txfUserName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        layoutPanel.add(lbBalance, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        layoutPanel.add(txfBalance, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;       
        layoutPanel.add(btnAdd, gbc);     
        add(layoutPanel);        
    }//end konstruktor   

    @Override
    public void actionPerformed(ActionEvent ev) {        
        try{
            String userName = txfUserName.getText();
            int balance = Integer.parseInt(txfBalance.getText());
            //konto = new BankAccount(userName, balance); 
           // AccountList.addAccountToList(new BankAccount(userName, balance));
            
            JOptionPane.showMessageDialog(null, "Konto skapat för " + userName);
       }catch(NumberFormatException nfe){
             JOptionPane.showMessageDialog(null, "Du måste ange rätt datatyp " );
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Något gick fel ");
        }
        txfUserName.setText("");
        txfBalance.setText("");
    }//end actionPerformed
    
    public static BankAccount getAccount(){
        return konto;
    }//end getAccount method
}//end class
