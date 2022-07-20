/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceUtilisateur;
import utils.LoginU;
import utils.Roots;


/**
 * FXML Controller class
 *
 * @author Khalil ZRIBI
 */
public class FXMLGestionProfilController implements Initializable {

    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtAge;
    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtGenre;
    @FXML
    private TextField TxtType;
    @FXML
    private TextField TxtTel;
    @FXML
    private Button BtnModifier;
    @FXML
    private Button BtnAccueil;
    @FXML
    private TextField TxtPass;
    @FXML
    private Button BtnModifierPass;
    
    Utilisateur l = LoginU.getUserconnect();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        TxtNom.setText(l.getNom());
        TxtPrenom.setText(l.getPrenom());
        TxtEmail.setText(l.getEmail());
        TxtAge.setText(String.valueOf(l.getAge()));
        TxtTel.setText(String.valueOf(l.getTelephone()));
        TxtGenre.setText(l.getGenre());
        TxtType.setText(l.getType());
        TxtPass.setText(l.getPassword());
    }    

    @FXML
    private void ModifierUtilisateur(ActionEvent event) {
        
        int id = l.getIdU();
        Utilisateur user = new Utilisateur();
        user.setIdU(id);
        user.setNom(TxtNom.getText());
        user.setPrenom(TxtPrenom.getText());
        user.setEmail(TxtEmail.getText());
        user.setAge(Integer.parseInt(TxtAge.getText()));
        user.setTelephone(Integer.parseInt(TxtTel.getText()));
        user.setGenre(TxtGenre.getText());
        user.setType(TxtType.getText());
       
        ServiceUtilisateur s = new ServiceUtilisateur();
        s.modifierU(user);

    }

    @FXML
    private void Retour(ActionEvent event) {
         try{
                Roots.roots("../GUI/FXMLVueMembre.fxml", event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
    }

    @FXML
    private void ModifierPassword(ActionEvent event) {
          int id = l.getIdU();
                  ServiceUtilisateur s = new ServiceUtilisateur();

        Utilisateur user = new Utilisateur();
        user.setIdU(id);
        user.setPassword(s.md5(TxtPass.getText()));
       
       // ServiceUtilisateur s = new ServiceUtilisateur();
        s.modifierUP(user);
        TxtPass.clear();
        
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  alert.setContentText("Modification Mot de passe avec succes");
        alert.getButtonTypes();  alert.showAndWait();
        }

    }
    
}
