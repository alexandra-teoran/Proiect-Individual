/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import model.Bill;


/**
 *
 * @author teora
 */
public class BillDao {
    public static String getId() {
        int id=1;
        try{
            ResultSet rs=BdOperations.getData("select max(id) from bill");
            if(rs.next()){
                id=rs.getInt(1);
                id++;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return String.valueOf(id);
    }
    
    public static void save(Bill bill) {
        String query="insert into bill values('"+bill.getId()+"', '"+bill.getName()+"', '"+bill.getPhoneNumber()+"', '"+bill.getEmail()+"', '"+bill.getDate()+"', '"+bill.getTotal()+"', '"+bill.getCreatedBy()+"')";
        BdOperations.setDataOrDelete(query, "Bill Details Added Successfully");
    }
    
    public static ArrayList<Bill> getAllRecordsByInc(String date) {
        ArrayList<Bill> arrayList=new ArrayList<>();
        try{
            ResultSet rs=BdOperations.getData("select * from bill where data like '%"+date+"%'");
            while(rs.next()){
                Bill bill=new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setPhoneNumber(rs.getString("nrTelefon"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("data"));
                bill.setTotal(rs.getString("total"));
                bill.setCreatedBy(rs.getString("autor"));
                arrayList.add(bill);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Bill> getAllRecordsByDesc(String date) {
        ArrayList<Bill> arrayList=new ArrayList<>();
        try{
            ResultSet rs=BdOperations.getData("select * from bill where data like '%"+date+"%' order by id desc");
            while(rs.next()){
                Bill bill=new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setPhoneNumber(rs.getString("nrTelefon"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("data"));
                bill.setTotal(rs.getString("total"));
                bill.setCreatedBy(rs.getString("autor"));
                arrayList.add(bill);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
}
