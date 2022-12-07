/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;

/**
 *
 * @author teora
 */
public class Tables {
    public static void main(String[] args) {
        try{
            String userTable="create table user(id int AUTO_INCREMENT primary key, nume varchar(200), email varchar(200), nrTelefon varchar(10), adresa varchar(200), parola varchar(200), intrebareDeSecuritate varchar(200), raspunsIntrebareSecuritate varchar(200), status varchar(20), UNIQUE (email))";
            String adminDetails="insert into user(nume, email, nrTelefon, adresa, parola, intrebareDeSecuritate, raspunsIntrebareSecuritate, status) values('Admin', 'admin@gmail.com', '1234567890', 'Romania', 'admin', 'What is your favoriute animal?', 'dog', 'true')";
            String categoryTable="create table category(id int AUTO_INCREMENT primary key, name varchar(200))";
            String productTable="create table product(id int AUTO_INCREMENT primary key, name varchar(200), category varchar(200), price varchar(200))";
            BdOperations.setDataOrDelete(userTable, "User Table Created Successfully");
            BdOperations.setDataOrDelete(adminDetails, "Admin Details Added Successfully");
            BdOperations.setDataOrDelete(categoryTable, "Category Table Created Successfully");
            BdOperations.setDataOrDelete(productTable, "Product Table Created Successfully");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
