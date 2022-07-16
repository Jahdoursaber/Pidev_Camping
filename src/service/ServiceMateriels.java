/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class ServiceMateriels {
    Connection cnx;

    public ServiceMateriels() {
        cnx = MyDb.getInstance().getConnection();
    }
    
     public void ajouter(Materiels m) {
        try {
             String req="INSERT INTO `camping`.`material` (`nom`,`description`, `refrence`, `photo`, `etat`, `prix`,`idCat`) values ( '"+m.getNom()+"','"+m.getDescription()
                     +"','"+m.getReference()+"','"+m.getPhoto()+"','"+m.getEtat()+"','"+m.getPrix()+"','"+m.getIdCat()+"')";

            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   

     }

    public void modifier(Materiels m) {
        String req = "UPDATE `material` SET `nom`=?,`description`=?,`refrence`=?,`photo`=? ,`etat`=?,`prix`=?,`idCat`=? WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.setString(1, m.getNom());
            pst.setString(2, m.getDescription());
            pst.setString(3, m.getReference());
            pst.setString(4, m.getPhoto());
            pst.setString(5, m.getEtat());
            pst.setFloat(6, m.getPrix());
            
            pst.setInt(7, m.getIdCat());
            pst.setInt(8, m.getId());
           
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
    


 public void supprimer(int id) {
        try {
            String req = "delete from material where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


public ObservableList<Materiels> getTout() {
     ObservableList <Materiels> materiels = FXCollections.observableArrayList();
     String req ="SELECT * FROM material   ";
     try{
         
     Statement stm =cnx.createStatement();
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Materiels m = new Materiels();
         m.setId(rst.getInt(1));
         m.setNom(rst.getString("nom"));
         m.setDescription(rst.getString("description"));
         m.setReference(rst.getString("refrence"));
         m.setPhoto(rst.getString("photo"));
         m.setEtat(rst.getString("etat"));
         m.setPrix(rst.getFloat("prix"));
         m.setIdCat(rst.getInt("idCat"));
         materiels.add(m);
         
     }
     
     }catch (SQLException ex){
         
         System.out.println(ex);
     }
       return materiels;
}  
}
