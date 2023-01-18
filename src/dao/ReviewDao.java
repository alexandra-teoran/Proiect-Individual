/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Review;
import java.sql.*;

/**
 *
 * @author teora
 */
public class ReviewDao {
    public static void save(Review review) {
        String query="insert into review(email, product_name, rating, review) values('"+review.getEmail()+"', '"+review.getProductName()+"', '"+review.getRating()+"', '"+review.getReview()+"')";
        BdOperations.setDataOrDelete(query, "Review Added Successfully");
    }
    
    public static ArrayList<Review> getAllRecordsByName(String name) {
        ArrayList<Review> arrayList=new ArrayList<>();
        try{
            ResultSet rs=BdOperations.getData("select * from review where product_name='"+name+"'");
            while(rs.next()){
                Review review=new Review();
                review.setEmail(rs.getString("email"));
                review.setProductName(rs.getString("product_name"));
                review.setRating(rs.getInt("rating"));
                review.setReview(rs.getString("review"));
                arrayList.add(review);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static int getAvgRating(String product_name) {
        int avgRating=0;
        try{
            ResultSet rs=BdOperations.getData("select avg(rating) from review where product_name='"+product_name+"'");
            if(rs.next())
                avgRating=rs.getInt(1);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return avgRating;
    }
    
}
