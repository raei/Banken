package lab4Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AccountDatabaseManager {

    private static Connection con;
    private static Statement stmt;

    private static void makeConnectionDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/userdbbank";
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
        } catch (SQLException sqle) {
            dialogBoxEvent("SQL Exception " + sqle.toString(), 1);
        } catch (ClassNotFoundException cnfe) {
            dialogBoxEvent("Class not found Exception " + cnfe.toString(), 2);
        } catch (Exception e) {
            dialogBoxEvent("Error " + e.toString(), 3);
        }
    }//end makeConnectionDb   

    public static void addAccountToDb(String userName, int balance) {
        try {
            makeConnectionDb();
            String insertQuery = "INSERT INTO `userdbbank`.`bankaccount`(`UserName`,`Balance`) VALUES('"
                    + userName + "','" + balance + "')";
            stmt.executeUpdate(insertQuery);
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            dialogBoxEvent("Fel vid ny kund DB", 1);
        }
    }//end addAccountToDb

    public static void deleteAccount(String userName) {
        try {
            makeConnectionDb();
            String deleteQuery = "DELETE FROM `bankaccount` WHERE `UserName` = '" + userName + "'";
            int row = stmt.executeUpdate(deleteQuery);
            dialogBoxEvent("Antal rader som togs bort " + row, 1);
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            dialogBoxEvent("Fel vid Delete", 1);            
        }
    }//end deleteAccount

    public static void updateAccountBalance(String userName, int balance) {
        try {
            makeConnectionDb();
            String updateQuery = "UPDATE `bankaccount` SET `Balance`=" + balance + " WHERE `UserName` = '" + userName + "'";
            int row = stmt.executeUpdate(updateQuery);
            dialogBoxEvent("Antal rader som uppdaterades " + row, 1);
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            dialogBoxEvent("Fel vid update", 1);
        }
    }//end updateAccountBalance

    public static BankAccount searchAccountInDb(String userName) {
        BankAccount konto = null;
        String namn;
        int balance;

        try {
            makeConnectionDb();
            String searchQuery = "SELECT `UserName`, `Balance` FROM `bankaccount` WHERE (`UserName` = '" + userName + "')";
            ResultSet result = stmt.executeQuery(searchQuery);
            while (result.next()) {
                namn = result.getString("UserName");
                balance = result.getInt("Balance");
                konto = new BankAccount(namn, balance);
            }//end while 
            stmt.close();
            con.close();
        } //end searchAccountInDb
        catch (SQLException ex) {
            dialogBoxEvent("Fel vid DB search", 1);
        }
        return konto;
    }//end searchAccountInDb

    private static void dialogBoxEvent(String msg, int type) {
        if (type == 1) {
            JOptionPane.showMessageDialog(null, msg,
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (type == 2) {
            JOptionPane.showMessageDialog(null, msg,
                    "Info", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, msg,
                    "Info", JOptionPane.ERROR_MESSAGE);
        }

    }//end dialogBoxEvent
}//end class
