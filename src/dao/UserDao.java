/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
/**
 *
 * @author teora
 */
public class UserDao {
    public static void save(User user){
        String query="insert into user(nume, email, nrTelefon, adresa, parola, intrebareDeSecuritate, raspunsIntrebareSecuritate, status) values('"+user.getName()+"', '"+user.getEmail()+"', '"+user.getPhoneNumber()+"', '"+user.getAddress()+"', '"+user.getPassword()+"', '"+user.getSecurityQuestion()+"', '"+user.getAnswer()+"', 'false')"; 
        BdOperations.setDataOrDelete(query, "Registered Successfully! Wait for Admin Approval!");
    }
    public static User login(String email, String password){
        User user=null;
        try{
            ResultSet rs=BdOperations.getData("select * from user where email='"+email+"' and parola='"+password+"'");
            while(rs.next()){
                user=new User();
                user.setStatus(rs.getString("status"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
    
    public static User getSecurityQuestion(String email){
        User user=null;
        try{
            ResultSet rs=BdOperations.getData("select * from user where email='"+email+"'");
            while(rs.next()){
                user=new User();
                user.setSecurityQuestion(rs.getString("intrebareDeSecuritate"));
                user.setAnswer(rs.getString("raspunsIntrebareSecuritate"));
            }
        }
        catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
    
    public static void update(String email, String newPassword){
        String query="update user set parola='"+newPassword+"' where email='"+email+"'";
        BdOperations.setDataOrDelete(query, "Password Changed Successfully");
    }
}
