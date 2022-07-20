/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncentre.Service;

import GestionCentre.Connexion.DataSource;
import gestioncentre.Entites.Centre;
import GestionCentre.Interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class CrudCentre implements IService<Centre>{
    
    Connection con;
    
    public CrudCentre(){
        DataSource c = DataSource.getInstance();
        con = c.getConnection();
        
      
    }
     

    
    public void add(Centre e) {
        String req="INSERT INTO `camping`.`centre_camping` (`id`, `Name`, `description`, `photo`, `addresse`) values (NULL, '"+e.getName()+"','"+e.getDescription() +"','"+e.getPhoto()+"','"+e.getAddresse()+"')"
                ;   
                try{
                    Statement stm = con.createStatement();
                    stm.executeUpdate(req);
                }catch(SQLException ex){
                    System.out.println("Erreur"+ex);
                }
                   
           }

    
   public ArrayList<Centre> getAll() {
     ArrayList<Centre> centre = new ArrayList<>();
     String req ="SELECT * FROM centre_camping";
     try{
         
     Statement stm =con.createStatement();
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Centre e = new Centre();
         e.setId(rst.getInt(1));
         e.setName(rst.getString("Name"));
         e.setDescription(rst.getString("description"));
         e.setPhoto(rst.getString("photo"));
         e.setAddresse(rst.getString("addresse"));
         centre.add(e);
         
     }
     
     }catch (SQLException ex){
         
         System.out.println(ex);
     }
       return centre;
    }
    
   
    
     public ObservableList<Centre> getTout() {
     ObservableList <Centre> centre = FXCollections.observableArrayList();
     String req ="SELECT * FROM centre_camping";
     try{
         
     Statement stm =con.createStatement();
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Centre e = new Centre();
         e.setId(rst.getInt(1));
         e.setName(rst.getString("Name"));
         e.setDescription(rst.getString("description"));
         e.setPhoto(rst.getString("photo"));
         e.setAddresse(rst.getString("addresse"));
         centre.add(e);
         
     }
     
     }catch (SQLException ex){
         
         System.out.println(ex);
     }
       return centre;
    }

    public void update(Centre e) {
        String req = "UPDATE `centre_camping` SET `Name`=?,`description`=?,`photo`=?,`addresse`=? WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(req);
           pst.setInt(4, e.getId());
            pst.setString(2, e.getName());
            pst.setString(1, e.getDescription());
            pst.setString(3, e.getAddresse());
            
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(Centre e) {
        String req = "DELETE FROM centre_camping where id = ?";
        
        try {
            PreparedStatement pst = con.prepareStatement(req);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public Centre getById(int id) throws SQLException{
        Centre e = new Centre();
        String req = "SELECT * FROM centre_camping WHERE id="+id;
        Statement stm = con.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
            e.setId(rst.getInt("id"));
            e.setName(rst.getString("Name"));
            e.setDescription(rst.getString("description"));
            e.setPhoto(rst.getString("photo"));
            e.setAddresse(rst.getString("addresse"));
                  
        }
        
        return e;
    }


}

