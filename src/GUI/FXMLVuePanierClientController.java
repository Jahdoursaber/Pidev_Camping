/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Materiels;
import entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import service.ServiceCategorie;
import service.ServiceMateriels;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLVuePanierClientController implements Initializable {

    @FXML
    private TextField recherche;
    
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
     private Map<Materiels,Integer>DataMat=new HashMap();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        
        this.DataMat=Panier.getMap_Panier();
        
        try {
            for (Map.Entry mapentry : this.DataMat.entrySet()) {
                 FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLVueMaterielPanier.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

               FXMLVueMaterielPanierController itemController = fxmlLoader.getController(); 
                itemController.setData((Materiels)mapentry.getKey(),(Integer)mapentry.getValue());
          
       
                if (column == 3) {
                    column = 0;
                    row++; 
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
        }    



    @FXML
    private void rechercheProduit(KeyEvent event) {
    }

    @FXML
    private void ConsulterProduit(MouseEvent event) {
         try {
              //récupération fichier fxml
              
              FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVueClient.fxml"));
              //récupération du root  à partir du fichier fxml
              
              Parent root = loader.load();
              //récupération du controller lier au fichier fxml
              
              FXMLVueClientController dpc = loader.getController();
              recherche.getScene().setRoot(root);
          } catch (IOException ex) {
              Logger.getLogger(FXMLVueAdminstrateurCategorieController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    
}
