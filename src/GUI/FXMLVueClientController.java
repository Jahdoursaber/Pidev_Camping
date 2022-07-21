/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categorie;
import entities.Materiels;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.ServiceCategorie;
import service.ServiceMateriels;
/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLVueClientController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private List<Materiels>DataMat=new ArrayList();
    @FXML
    private ComboBox<Categorie> filtreCat;
    @FXML
    private Button all;
    @FXML
    private TextField recherche;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       int column = 0;
        int row = 1;
        ServiceMateriels sm =new ServiceMateriels();
        ServiceCategorie sc= new ServiceCategorie();
        filtreCat.getItems().addAll(sc.getTout());
        
        this.DataMat.addAll(sm.getTout());
        
        try {
            for (int i = 0; i < this.DataMat.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLVueMateriels.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLVueMaterielsController itemController = fxmlLoader.getController(); 
                itemController.setData(this.DataMat.get(i));

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
    private void ChangeView(ActionEvent event) {
       this.DataMat.clear();
       grid.getChildren().removeAll(grid.getChildren());
        Categorie cat = filtreCat.getSelectionModel().getSelectedItem();
      int row=1;
      int column=0;
        ServiceMateriels sm =new ServiceMateriels();
      
      this.DataMat.addAll(sm.getTout().stream().filter(e->e.getIdCat()==cat.getId()).collect(Collectors.toList()));
        
        try {
            for (int i = 0; i < this.DataMat.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLVueMateriels.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLVueMaterielsController itemController = fxmlLoader.getController();
                itemController.setData(this.DataMat.get(i));

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
    private void filtreoff(ActionEvent event) {
              this.DataMat.clear();

       grid.getChildren().removeAll(grid.getChildren());
       int column = 0;
        int row = 1;
        ServiceMateriels sm =new ServiceMateriels();
        ServiceCategorie sc= new ServiceCategorie();
        filtreCat.getItems().clear();
        filtreCat.getItems().addAll(sc.getTout());        
        this.DataMat.addAll(sm.getTout());
        
        try {
            for (int i = 0; i < this.DataMat.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLVueMateriels.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLVueMaterielsController itemController = fxmlLoader.getController();
                itemController.setData(this.DataMat.get(i));

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
        String product_name=this.recherche.getText();
        
        this.DataMat.clear();

       grid.getChildren().removeAll(grid.getChildren());
       int column = 0;
        int row = 1;
        ServiceMateriels sm =new ServiceMateriels();
        ServiceCategorie sc= new ServiceCategorie();
        filtreCat.getItems().clear();
        filtreCat.getItems().addAll(sc.getTout()); 
        if(recherche.getText().compareTo("")!=0)
        this.DataMat.addAll(sm.getTout().stream().filter(p->p.getNom().contains(product_name)).collect(Collectors.toList()));
        else
        this.DataMat.addAll(sm.getTout());
        
       
        try {
            for (int i = 0; i < this.DataMat.size(); i++) {    
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLVueMateriels.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLVueMaterielsController itemController = fxmlLoader.getController();
                itemController.setData(this.DataMat.get(i));

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
    private void ConsulterPanier(MouseEvent event) {
         try {
              //récupération fichier fxml
              
              FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVuePanierClient.fxml"));
              //récupération du root  à partir du fichier fxml
              
              Parent root = loader.load();
              //récupération du controller lier au fichier fxml
              
              FXMLVuePanierClientController dpc = loader.getController();
              all.getScene().setRoot(root);
          } catch (IOException ex) {
              Logger.getLogger(FXMLVueAdminstrateurCategorieController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
}
