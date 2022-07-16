/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Categorie;
import entities.Materiels;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDb;

/**
 *
 * @author Dell
 */
public class ServiceCategorie {
    Connection cnx;

    public ServiceCategorie() {
        cnx = MyDb.getInstance().getConnection();
    }
    
     public void ajouter(Categorie c) {
        try {
             String req="INSERT INTO categorie values ( '"+c.getId()+"','"+c.getLabel()+"')";

            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   

     }

    public void modifier(Categorie c) {
        String req = "UPDATE `categorie` SET `label`=? WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.setString(1, c.getLabel());
            pst.setInt(2, c.getId());
           
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
    


 public void supprimer(int id) {
        try {
             String req="DELETE FROM `categorie` WHERE `id` = "+ id;

            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


public ObservableList<Categorie> getTout() {
     ObservableList <Categorie> categories = FXCollections.observableArrayList();
     String req ="SELECT * FROM categorie   ";
     try{
         
     Statement stm =cnx.createStatement();
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Categorie c = new Categorie();
         c.setId(rst.getInt(1));
         c.setLabel(rst.getString("label"));
       
       
         categories.add(c);
         
     }
     
     }catch (SQLException ex){
         
         System.out.println(ex);
     }
       return categories;
}  
}
