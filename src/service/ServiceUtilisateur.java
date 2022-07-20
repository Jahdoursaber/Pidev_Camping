/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Utilisateur;
import static java.lang.Integer.parseInt;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.LoginU;
import utils.MyDb;

/**
 *
 * @author Khalil ZRIBI
 */
public  class ServiceUtilisateur {
       
    Connection cnx;

    public ServiceUtilisateur() {
        
        cnx = MyDb.getInstance().getCnx();
    }
    
     public void ajouter(Utilisateur u) {
        try {
             String req="INSERT INTO `camping`.`user` (`nom`,`prenom`, `email`, `age`, `telephone`, `password`,"
                     + "`genre`, `type`, `role`) values ( '"+u.getNom()+"','"+u.getPrenom()
                     +"','"+u.getEmail()+"','"+u.getAge()+"','"+u.getTelephone()+"','"+u.getPassword()
                     +"','"+u.getGenre()+"','"+u.getType()+"','"+"membre"+"')";

            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   

     }

    public void modifier(Utilisateur t) {
////        String req = "UPDATE `user` SET `nom`=?,`prenom`=?,`email`=?,`age`=? ,`telephone`=?,`password`=?,`genre`=? ,`type`=?,`role`=? WHERE id = ?";
////        try {
////            PreparedStatement pst = cnx.prepareStatement(req);
////           
////            pst.setString(1, u.getNom());
////            pst.setString(2, u.getPrenom());
////            pst.setString(3, u.getEmail());
////            pst.setInt(4, u.getAge());
////            pst.setInt(5, u.getTelephone());
////           // pst.setString(6, u.getPassword());
////            pst.setString(7, u.getType());
////            pst.setString(8, u.getType());
////            pst.setString(9, u.getRole());
////            
////           
////           
////            pst.executeUpdate();
////
////        } catch (SQLException ex) {
////            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
////        }

try {
            System.out.println(t.getIdU());
            String qry = "UPDATE user SET nom='" + t.getNom() + "', prenom='" + t.getPrenom() + "', email='"
                    + t.getEmail()+ "', age='" + t.getAge()+ "', telephone='" + t.getTelephone()+ "', genre='" + t.getGenre()+ "', type='" + t.getType()+ "', role='" + t.getRole() + "' WHERE  id='" + t.getIdU()+ "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(qry);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
        
    public void modifierU(Utilisateur t) {
        try {
            System.out.println(t.getIdU());
            String qry = "UPDATE user SET nom='" + t.getNom() + "', prenom='" + t.getPrenom() + "', email='"
                    + t.getEmail()+ "', age='" + t.getAge()+ "', telephone='" + t.getTelephone()+ "', genre='" + t.getGenre()+ "', type='" + t.getType() + "' WHERE  id='" + t.getIdU()+ "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(qry);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
           
           
    }


 public void supprimer(int id) {
        try {
            String req = "delete from user where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


public ObservableList<Utilisateur> getTout() {
     ObservableList <Utilisateur> utilisateur = FXCollections.observableArrayList();
     String req ="SELECT * FROM user   ";
     try{
         
     Statement stm =cnx.createStatement();
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Utilisateur m = new Utilisateur();
         m.setIdU(rst.getInt(1));
         m.setNom(rst.getString("nom"));
         m.setPrenom(rst.getString("prenom"));
         m.setEmail(rst.getString("email"));
         m.setAge(rst.getInt("age"));
         m.setTelephone(rst.getInt("telephone"));
         m.setPassword(rst.getString("password"));
         m.setGenre(rst.getString("genre"));
         m.setType(rst.getString("type"));
         m.setRole(rst.getString("role"));
         utilisateur.add(m);
         
     }
     
     }catch (SQLException ex){
         
         System.out.println(ex);
     }
       return utilisateur;
}  

public void logout() {
        LoginU.setInstance(null);
    }

    public Utilisateur login(String email, String password) {
        Utilisateur p = new Utilisateur();
        try {
            String qry = "SELECT * FROM `user` WHERE email='" + email + "' and password='" + password + "'";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            if (rs.first()) {
                 p.setIdU(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                p.setAge(parseInt(rs.getString("age")));
                p.setTelephone(parseInt(rs.getString("telephone")));
                p.setGenre(rs.getString("genre"));
                p.setType(rs.getString("type"));
                p.setRole(rs.getString("role"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    public String md5(String text) {
       
            MessageDigest md;
            String generatedPassword =null;
        try {
            md = MessageDigest.getInstance("MD5");
             // Add password bytes to digest
            md.update(text.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();
            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
         return generatedPassword;  
            
    }
    
    public ObservableList<Utilisateur> setuser(List<Utilisateur> a) {
        ObservableList<Utilisateur> u = FXCollections.observableArrayList();
        for (Utilisateur e : a) {
            u.add(e);
        }
        return u;

    }

    public void modifierUP(Utilisateur t) {
          try {
            System.out.println(t.getIdU());
            String qry = "UPDATE user SET password='" + t.getPassword()+  "' WHERE  id='" + t.getIdU()+ "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(qry);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
}
