/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.MyDb;

/**
 *
 * @author MALEK
 */
public class CrudReclamation implements Iservice<Reclamation> {

    Connection cnx = MyDb.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation t) {
        try {
            
//            user l = LoginU.getUserconnect
//          String req = "INSERT INTO reclamation(description,etat,idType,id_user )VALUES(?,?,?,?)";
            String req = "INSERT INTO reclamation(description,etat,idType,id_user )VALUES(?,?,?,2)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getDescription());
            pst.setString(2, t.getEtat());//
            pst.setInt(3, t.getIdType());
            pst.executeUpdate();
            System.out.println("Reclamation Ajoutée !!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Reclamation t) {
        try {
            String req = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Reclamation suprimée !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void modifier(Reclamation t) {
        try {
            String req = "UPDATE reclamation SET description=?,etat=?, idType=?WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(4, t.getId());
            pst.setString(1, t.getDescription());
            pst.setString(2, t.getEtat());
            pst.setInt(3, t.getIdType());
            pst.executeUpdate();
            System.out.println("Reclamation " + t.getDescription() + " Modifiée !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req = "SELECT * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getString("description"), rs.getString("etat"), rs.getInt("idType")));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return list;
    }

    public List<CrudKeyValue> ListJoiture() {
        List<CrudKeyValue> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `reclamation` as re , `typereclamation` as te WHERE re.idType=te.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new CrudKeyValue(rs.getInt("id"), rs.getString("description"), rs.getString("etat"), rs.getString("type")));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return list;
    }

    public String makeFine(String val) {
        List<String> badWords = new ArrayList<>();
        badWords.add("putin");
        badWords.add("shit");
        badWords.add("merde");
        badWords.add("shitty");
        badWords.add("fuck");
        badWords.add("motherlucker");

        String[] splited = val.split("\\s+");//split  bel espace
        String newval = "";//where we gonna stock
        for (String word : splited) {
            String stars = "";//string for affectings stars
            for (String bad : badWords) {
                if (word.toLowerCase().equals(bad.toLowerCase())) {//low or uppercase
                    for (int i = 0; i <= word.length() - 1; i++) {
                        stars += "*";//get the stars
                    }

                    newval += stars + " ";//affect it to newval
                    break;
                }
            }
            if (stars.equals("")) {
                newval += word + " ";//concat
            }
        }
        return newval;
    }

    public List<CrudKeyValue> Search(String t) {

        List<CrudKeyValue> list1 = new ArrayList<>();
        List<CrudKeyValue> list2 = ListJoiture();
        list1 = (list2.stream().filter(c -> c.getEtat().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }

}
