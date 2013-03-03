package lab4;

import java.awt.Dimension;
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

public class SearchAccount extends JPanel{
    
    private JPanel layoutPanel;
    private JLabel lbSearch, lbAccountName, lbAccountBalance, lbDeposit, lbWithdraw;
    private JTextField txfSearch, txfAccountName, txfAccountBalance, txfDeposit, txfWithdraw ;
    private JButton btnSearch, btnRemove, btnDeposit, btnWithdraw;
    private Border loweredbevel;
    private static BankAccount actualAccount;
    
    public SearchAccount(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        layoutPanel = new JPanel();
        layoutPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 5, 2); 
        //build labels
        lbSearch = new JLabel("Search:");
        lbAccountName = new JLabel("Account name:");
        lbAccountBalance = new JLabel("Balance:");
        lbDeposit = new JLabel("Deposit");
        lbWithdraw = new JLabel("Withdraw:");
        //build texfields        
        txfSearch = new JTextField(15);
        txfAccountName = new JTextField(15);
        txfAccountBalance = new JTextField(15);
        txfDeposit = new JTextField(15);
        txfWithdraw = new JTextField(15);
        //build buttons
        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(100, 18));
        btnSearch.addActionListener(new SerchListener());
        
        btnRemove = new JButton("Remove");
        btnRemove.setPreferredSize(new Dimension(100, 18));
        btnRemove.addActionListener(new RemoveListener());
        
        btnDeposit = new JButton("Deposit");
        btnDeposit.setPreferredSize(new Dimension(100, 18));
        btnDeposit.addActionListener(new DepositListener());
        
        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setPreferredSize(new Dimension(100, 18));
        btnWithdraw.addActionListener(new WithDrawListener());
        
        
        gbc.anchor = GridBagConstraints.EAST;//Flyttar  all objekt till höger
        //första raden
        gbc.gridx = 0;
        gbc.gridy = 0;        
        layoutPanel.add(lbSearch, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        layoutPanel.add(txfSearch, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;       
        layoutPanel.add(btnSearch, gbc);
        
        //andra raden
        gbc.gridx = 0;
        gbc.gridy = 1;
        layoutPanel.add(lbAccountName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        layoutPanel.add(txfAccountName, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        layoutPanel.add(btnRemove, gbc);
        
        //tredje raden
        gbc.gridx = 0;
        gbc.gridy = 2;
        layoutPanel.add(lbAccountBalance, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        layoutPanel.add(txfAccountBalance, gbc);
        
        //fjärde raden
        gbc.gridx = 0;
        gbc.gridy = 3;
        layoutPanel.add(lbDeposit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        layoutPanel.add(txfDeposit, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 3;
        layoutPanel.add(btnDeposit, gbc);
        
        //femte raden
        gbc.gridx = 0;
        gbc.gridy = 4;
        layoutPanel.add(lbWithdraw, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        layoutPanel.add(txfWithdraw, gbc);        
        gbc.gridx = 2;
        gbc.gridy = 4;
        layoutPanel.add(btnWithdraw, gbc);  
        
        add(layoutPanel);//lägger till huvudpanelen        
    }//end constructor  

    private class RemoveListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Ger en dialogruta för att kunna ångra sig
           int n = JOptionPane.showConfirmDialog(null,
                    "Would you like to delete " + actualAccount.getOwnerName() +  " account?",
                    "Remove this account",
                    JOptionPane.YES_NO_OPTION); 
           if(n == JOptionPane.YES_OPTION){
              // AccountList.removeAccountFromList(actualAccount);
               txfSearch.setText("");
               txfAccountName.setText("");
               txfAccountBalance.setText("");
           }           
        }
    }//end RemoveListener

    private class WithDrawListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //kontot = AddAccount.getAccount();
                int withdrawAmount = Integer.parseInt(txfWithdraw.getText());
                if(actualAccount.withdraw(withdrawAmount)){
                     txfAccountBalance.setText("" +actualAccount.getBalance());
                    JOptionPane.showMessageDialog(null, "Du tog ut " + withdrawAmount + "\nDitt saldo är nu: " +actualAccount.getBalance());   
                }else{
                    JOptionPane.showMessageDialog(null, "Uttag medges ej.\nGör ett nytt försök.");
                }
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Du måste ange ett tal");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Något gick fel");
            }
                 txfWithdraw.setText("");
        }//end actionPerformed       
    }//end Withdrawlistener innerclass

    private class DepositListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           try{ 
               //kontot = AddAccount.getAccount();
               int amount = Integer.parseInt(txfDeposit.getText());
               boolean koll = actualAccount.deposit(amount);
               if(koll == true){
                   txfAccountBalance.setText("" +actualAccount.getBalance());
                   JOptionPane.showMessageDialog(null, "Du satt in " + amount + "\nDitt saldo är nu: " +actualAccount.getBalance());               
               }else{
                   JOptionPane.showMessageDialog(null, "Gör ett nytt försök ");             
               }
           }catch(NumberFormatException nfe){
               JOptionPane.showMessageDialog(null, "Du måste ange ett tal");
           }catch(Exception ex){
               JOptionPane.showMessageDialog(null, "Något gick fel");
           }
            txfDeposit.setText("");
        }//end actionPerformed        
    }//end DepositListener innerclass

    private class SerchListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String namn = txfSearch.getText();
                //actualAccount = AccountList.searchAccountInList(namn);
                if(actualAccount != null){
                    txfAccountName.setText(actualAccount.getOwnerName());
                    txfAccountBalance.setText("" + actualAccount.getBalance());
                }else{
                    JOptionPane.showMessageDialog(null, "Kontot du sökte fanns ej"); 
                }
            }catch(NullPointerException nex){
                JOptionPane.showMessageDialog(null, "Du måste skapa ett konto först"); 
                txfSearch.setText("");
            }//end try catch           
        }//end actionPerformed
    }//end SearchListener  innerclass  
}//end class
