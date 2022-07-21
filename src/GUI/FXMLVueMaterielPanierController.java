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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLVueMaterielPanierController implements Initializable {

    @FXML
    private Label prixlabel;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label quantite;
    @FXML
    private Button up;
    @FXML
    private Button down;
    
    private int quantitevalue;
    private Materiels m ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void upquantite(ActionEvent event) {
        Panier.upQuantite(m);
        this.quantitevalue=this.quantitevalue+1;
        this.quantite.setText(String.valueOf(this.quantitevalue));
         this.down.setDisable(false);

        
    }

    @FXML
    private void downquantite(ActionEvent event) {
        if(this.quantitevalue==2){
        Panier.downQuantite(m);
        this.quantitevalue=this.quantitevalue-1;
        this.quantite.setText(String.valueOf(this.quantitevalue));
        this.down.setDisable(true);
        }else{ //cas >2 
           
          Panier.downQuantite(m);
        this.quantitevalue=this.quantitevalue-1;
        this.quantite.setText(String.valueOf(this.quantitevalue));
        }
    }
    public void setData(Materiels mat,int quantite) {
        this.m=mat;
        this.quantitevalue=quantite;
         this.down.setDisable(true);

        
        this.nameLabel.setText(mat.getNom());
        
        this.prixlabel.setText(String.valueOf(mat.getPrix()));
        Image image = new Image(getClass().getResourceAsStream("\\image\\"+mat.getPhoto()));
        img.setImage(image);
        this.quantite.setText(String.valueOf(quantite));
        
    }
    
}
