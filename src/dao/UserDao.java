/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.util.ArrayList;
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
    
    public static ArrayList<User> getAllRecords(String email) {
        ArrayList<User> arrayList=new ArrayList<>();
        try {
            ResultSet rs=BdOperations.getData("select * from user where email like '%"+email+"%'");
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nume"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("nrTelefon"));
                user.setAddress(rs.getString("adresa"));
                user.setSecurityQuestion(rs.getString("intrebareDeSecuritate"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static User getUser(String email) {
        User user=new User();
        try{
            ResultSet rs=BdOperations.getData("select * from user where email like '%"+email+"%'");
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("nume"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("nrTelefon"));
            user.setAddress(rs.getString("adresa"));
            user.setSecurityQuestion(rs.getString("intrebareDeSecuritate"));
            user.setStatus(rs.getString("status"));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
    
    public static void changeStatus(String email, String status) {
        String query="update user set status='"+status+"' where email='"+email+"'";
        BdOperations.setDataOrDelete(query, "Status Changed Successfully");
    }
    
    public static void changePassword(String email, String oldPassword, String newPassword) {
        try{
            ResultSet rs=BdOperations.getData("select * from user where email='"+email+"' and parola='"+oldPassword+"'");
            if(rs.next())
                update(email, newPassword);
            else
                JOptionPane.showMessageDialog(null, "Old Password is Wrong");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void changeSecurityQuestion(String email, String password, String securityQuestion, String answer){
        try{
            ResultSet rs=BdOperations.getData("select * from user where email='"+email+"' and parola='"+password+"'");
            if(rs.next()){
                update(email, securityQuestion, answer);
            }
            else
                JOptionPane.showMessageDialog(null, "Password is wrong");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void update(String email, String securityQuestion, String answer){
        String query="update user set intrebareDeSecuritate='"+securityQuestion+"', raspunsIntrebareSecuritate='"+answer+"' where email='"+email+"'";
        BdOperations.setDataOrDelete(query, "Security Question Changed Successfully");
    }
    
}
