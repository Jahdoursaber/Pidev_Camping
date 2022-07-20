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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import service.ServiceUtilisateur;
import utils.Roots;

/**
 * FXML Controller class
 *
 * @author Khalil ZRIBI
 */
public class FXMLRegisterController implements Initializable {

    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtAge;
    @FXML
    private TextField TxtTelephone;
    @FXML
    private PasswordField TxtPass;
    @FXML
    private RadioButton RadBtnHomme;
    @FXML
    private ToggleGroup genre;
    @FXML
    private RadioButton RadBtnFemme;
    @FXML
    private RadioButton RadBtnTun;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton RadBtnEtr;
    @FXML
    private Button BtnInscription;
    @FXML
    private Button BtnCnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Inscription(MouseEvent event) {
        if (TxtNom.getText().equals("") || TxtPrenom.getText().equals("") || TxtEmail.getText().equals("")
                || Integer.parseInt(TxtAge.getText()) == 0 || Integer.parseInt(TxtTelephone.getText()) == 0
                ||TxtPass.getText().equals("")
                || (genre.getSelectedToggle() == null) || (type.getSelectedToggle() == null))
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);  alert.setContentText("Tous les champs sont obligatoires");
        alert.getButtonTypes();  alert.showAndWait();
        }else {  
            RadioButton selectedRadioButtonG = (RadioButton) genre.getSelectedToggle();
            String toogleGroupValueG = selectedRadioButtonG.getText().toLowerCase();

            RadioButton selectedRadioButtonTy = (RadioButton) type.getSelectedToggle();
            String toogleGroupValueTy = selectedRadioButtonTy.getText().toLowerCase();

                    ServiceUtilisateur se =new ServiceUtilisateur();
                    String generatedPassword = se.md5(TxtPass.getText());

                    Utilisateur u = new Utilisateur(TxtNom.getText(), TxtPrenom.getText(), TxtEmail.getText(),Integer.parseInt(TxtAge.getText()),Integer.parseInt(TxtTelephone.getText()) , generatedPassword, toogleGroupValueG, toogleGroupValueTy,"membre");
                    
                    se.ajouter(u);
                    
                   TxtNom.clear();
                   TxtPrenom.clear();
                   TxtEmail.clear();
                   TxtAge.clear();
                   TxtTelephone.clear();
                   TxtPass.clear();
                   
                   
                   {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  alert.setContentText("Inscription avec succes");
        alert.getButtonTypes();  alert.showAndWait();
        }
        }
    }
    @FXML
    private void connexion(ActionEvent event) {
        try{
                Roots.roots("../GUI/FXMLLoginUser.fxml", event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        
    }
    
}
