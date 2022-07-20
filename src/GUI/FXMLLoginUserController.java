/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.ServiceUtilisateur;
import utils.LoginU;
import utils.Roots;

/**
 * FXML Controller class
 *
 * @author Khalil ZRIBI
 */
public class FXMLLoginUserController implements Initializable {

    @FXML
    private TextField TxtEmail;
    @FXML
    private PasswordField TxtPass;
    @FXML
    private Button BtnLogin;
    @FXML
  
    private Button BtnInscription;
    @FXML
    private Label LbErreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActionConnexion(ActionEvent event) {
        ServiceUtilisateur s = new ServiceUtilisateur();
        
       LoginU l = LoginU.getInstance(TxtEmail.getText(),s.md5(TxtPass.getText()));
        System.out.println("==============="+l.getUserconnect().getRole());
        if (l.getUserconnect().getEmail()!= null) {
            try{
               String role= l.getUserconnect().getRole();
                if(l.getUserconnect().getRole().equals("admin")){
                      System.out.println("==============="+l.getUserconnect().getEmail());
                    Roots.roots("../GUI/FXMLVueAdmin.fxml", event);
                }else if(l.getUserconnect().getRole().equals("membre")){
                    Roots.roots("../GUI/FXMLVueMembre.fxml", event);
                }else if(l.getUserconnect().getRole().equals("adminShop")){
                   Roots.roots("../GUI/FXMLVueAdminShop.fxml", event); 
                }
              //  Roots.roots("../GUI/FXMLAccueilAdmin.fxml", event);
                
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        } else {
         
            //ServiceUtilisateur s = new ServiceUtilisateur();
            s.logout();
         }
       
        TxtEmail.clear();
        TxtPass.clear();
    
    }

    @FXML
    private void Inscription(ActionEvent event) {
         try{
                Roots.roots("../GUI/FXMLRegister.fxml", event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
    }
    
}
