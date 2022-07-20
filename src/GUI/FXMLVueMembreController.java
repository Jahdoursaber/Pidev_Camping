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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.LoginU;
import utils.Roots;

/**
 * FXML Controller class
 *
 * @author Khalil ZRIBI
 */
public class FXMLVueMembreController implements Initializable {

    @FXML
    private Button BtnProfil;
    @FXML
    private Label LNom;
    @FXML
    private Button BtnDeconnexion;

     Utilisateur user= LoginU.getUserconnect();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                        LNom.setText(user.getNom());

    }    

    @FXML
    private void ConsulterProfil(ActionEvent event) {
        try{
                Roots.roots("../GUI/FXMLGestionProfil.fxml", event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
    }

    @FXML
    private void Deconnexion(ActionEvent event) {try{
        
            LoginU.setInstance(null);
                Roots.roots("../GUI/FXMLLoginUser.fxml", event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        
    }
    
}
