/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Materiels;
import entities.Panier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLVueMaterielsController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label prixlabel;
    @FXML
    private Button ajouterPanier;
    private Materiels m;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setData(Materiels mat) {
        this.m=mat;
        this.nameLabel.setText(mat.getNom());
        
        this.prixlabel.setText(String.valueOf(mat.getPrix()));
        Image image = new Image(getClass().getResourceAsStream("\\image\\"+mat.getPhoto()));
        img.setImage(image);
    }

    @FXML
    private void ajouterPanierFn(ActionEvent event) {
        Panier.ajouterPanier(m, 1);
        ajouterPanier.setDisable(true);
        
        Alert a = new Alert(Alert.AlertType.NONE);

                a.setAlertType(Alert.AlertType.INFORMATION);
 
                // set content text
                a.setContentText(" Consultez votre panier ");
 
                // show the dialog
                a.show();
    }
    
}
