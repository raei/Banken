/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5Demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login 
{
    private static String OK = "jBLogin";
    private static String HELP = "help";
     private Statement stmt;
    private Connection con;
    private  ArrayList<String> passwordList;
    private ArrayList<String> userNameList;
  

    public Login() 
    {
           passwordList = new ArrayList<>(); 
           userNameList = new ArrayList<>();
           dialogen();
    }
    
    private void dialogen()
    {
        
        boolean passwordOk;//check passw
        boolean userNameOk;//check username
        boolean loop = true;//control the whileloop
         
        char[] temp;
        char[] inputPass; 
        String inputUser;
        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JPasswordField password = new JPasswordField();
        Object[] ob = {jUserName, userName, jPassword, password};
        
        while(loop)
        {     
            passwordOk = false;
            userNameOk = false;
            int result = JOptionPane.showConfirmDialog(null, ob, "Login Handelsbanken", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) 
            {          
                inputPass = password.getPassword();
                inputUser = userName.getText();
                connect(inputUser);
                for (String string : passwordList) //check DB listan om passw stämmer
                {
                    temp = string.toCharArray();
                    if(Arrays.equals(inputPass, temp))
                    {
                        passwordOk = true;              
                    }
                }//end for

                for (String string : userNameList) //check DB listan om username stämmer
                {                                    
                    if(inputUser.equals(string)){
                        userNameOk = true;              
                    }
                }//end for

                if (passwordOk== true && userNameOk == true) {
                    //dialogBoxEvent("Welcome ", 1);
                    //Zero out the possible password, for security.
                    Arrays.fill(inputPass, '0');
                    password.selectAll(); 
                    loop = false;//end loop if login ok
                }else if(passwordOk == false && userNameOk == true){
                    dialogBoxEvent("Wrong password ", 1);
                    password.setText("");
                }else if(userNameOk == false && passwordOk == true){
                    dialogBoxEvent("Wrong username ", 1);
                    userName.setText("");
                }else{
                    dialogBoxEvent("Wrong username and password ", 2);
                    userName.setText("");
                    password.setText("");                                       
                }
                
            }else if(result == JOptionPane.CANCEL_OPTION){// om du klickar på avbryt knappen
                System.exit(0);
                loop = false;
            }//end if else if                 
        }//END WHILE              
    }//end dialogen
       
     private void connect(String inputUser)                                          
    {                                              
        makeConnectionDb(); //koppla upp mot db
        try {        
         ResultSet rs = stmt.executeQuery("SELECT UserName, Password from users where UserName='" + inputUser+"';");
       
         while(rs.next())
         {           
           passwordList.add(rs.getString("Password"));//Add to array
           userNameList.add(rs.getString("UserName"));//Add to array
         }//end while loop
         con.close();
        }catch(SQLException sqle){
            dialogBoxEvent("SQL Exception " + sqle.toString(), 1);            
        }catch( Exception e ){
            dialogBoxEvent("Error " + e.toString(), 3);           
        }//end catch    
    }   //end actionP
     
     private void makeConnectionDb()
    {
      try {         
         Class.forName("com.mysql.jdbc.Driver");
         String url ="jdbc:mysql://localhost:3306/userdbbank";
         con = DriverManager.getConnection(url,"root", "");
         stmt = con.createStatement();
        }catch(SQLException sqle){
            dialogBoxEvent("SQL Exception " + sqle.toString(), 1);            
        }catch(ClassNotFoundException cnfe){
            dialogBoxEvent("SQL Exception " + cnfe.toString(), 2);            
        }catch( Exception e ){
            dialogBoxEvent("Error " + e.toString(), 3);           
        }
    }//end makeConnectionDb
    
    private void dialogBoxEvent(String msg, int type){
        
        if(type == 1){
            JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
        }else if(type == 2){
            JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.ERROR_MESSAGE);
        }
    
    }//end dialogBoxEvent   
}
