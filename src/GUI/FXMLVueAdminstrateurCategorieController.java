/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categorie;
import entities.Materiels;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import service.ServiceCategorie;
import service.ServiceMateriels;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLVueAdminstrateurCategorieController implements Initializable {
   
    
    @FXML
    private TextField chercherInput;
    @FXML
    private TableView<Categorie> datatable;
    @FXML
    private Button AjouterButton;
    
    @FXML
    private Button modifierButton;
    @FXML
    private AnchorPane anchorForm;
    @FXML
    private Text fileName;
    @FXML
    private Button save;
    @FXML
    private Button Annuler;
    private String formAction;
    @FXML
    private TableColumn<Categorie, Integer> iddata;
    @FXML
    private TableColumn<Categorie, String> labeldata;
    @FXML
    private TextField inputlabel;
    @FXML
    private Button supButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.formAction="ajouter";
        showdata();
        
        this.supButton.setDisable(true);
        this.modifierButton.setDisable(true);
        this.anchorForm.setOpacity(0);
         ObservableList selectedCells = datatable.getSelectionModel().getSelectedCells(); // les lignes eli sar 3lahom el selection 
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Categorie cat = (Categorie) datatable.getSelectionModel().getSelectedItem();
                if(cat!=null){
                   supButton.setDisable(false);
                   modifierButton.setDisable(false);
                }else{
                      supButton.setDisable(true);
                   modifierButton.setDisable(true);
                }
            }
           
        });


    } 
    
    
    
    public void showdata(){
        ServiceCategorie sc = new ServiceCategorie();
         ObservableList<Categorie> list = FXCollections.observableArrayList();
          iddata.setCellValueFactory(new PropertyValueFactory<>("id"));
        labeldata.setCellValueFactory(new PropertyValueFactory<>("label"));
        list.addAll(sc.getTout());
        datatable.setItems(list);
        
        
    }
     @FXML
    private void submitForm(ActionEvent event) {
        ServiceCategorie sc = new ServiceCategorie();

      if(this.formAction.compareTo("ajouter")==0){
    //Float.valueof(string input ) : Float output 
    
        Categorie c = new Categorie(0,this.inputlabel.getText()); 
        sc.ajouter(c);
           this.inputlabel.setText("");
                Alert a = new Alert(Alert.AlertType.NONE);

                a.setAlertType(Alert.AlertType.INFORMATION);
 
                // set content text
                a.setContentText(" Categorie ajouté");
 
                // show the dialog
                a.show();
         showdata();
         this.anchorForm.setOpacity(0);
      }else{
          //modifier
               Categorie c = (Categorie) datatable.getSelectionModel().getSelectedItem();
        Categorie c1 = new Categorie(c.getId(),this.inputlabel.getText());
        sc.modifier(c1);
           this.inputlabel.setText("");
              
                Alert a = new Alert(Alert.AlertType.NONE);

                a.setAlertType(Alert.AlertType.INFORMATION);
 
                // set content text
                a.setContentText(" Categorie Modifieé");
 
                // show the dialog
                a.show();
         showdata();
         this.save.setText("Ajouter");
         this.anchorForm.setOpacity(0);

        
    
        
      }
        
         
          
       
        
    }

    @FXML
    private void CloseForm(ActionEvent event) {
           this.anchorForm.setOpacity(0);
                this.inputlabel.setText("");
    }


    @FXML
    private void showFormAjouter(ActionEvent event) {
this.formAction="ajouter";
                this.anchorForm.setOpacity(1);
    }

    @FXML
    private void showFormModifer(ActionEvent event) {
        this.formAction="modifier";
        this.anchorForm.setOpacity(1);
             Categorie cat = (Categorie) datatable.getSelectionModel().getSelectedItem();
       this.inputlabel.setText(cat.getLabel());
       this.save.setText("Modifier");
       
        
    }


    @FXML
    private void ChercherMat(KeyEvent event) {
       if(this.chercherInput.getText().compareTo("")==0){
            showdata();
        }else{
            String motcle=this.chercherInput.getText();
            ServiceCategorie sc = new ServiceCategorie();
         ObservableList<Categorie> list = FXCollections.observableArrayList();
          iddata.setCellValueFactory(new PropertyValueFactory<>("id"));
        labeldata.setCellValueFactory(new PropertyValueFactory<>("label"));
        list.addAll(sc.getTout().stream().filter(c-> c.getLabel().contains(motcle)
                ||String.valueOf(c.getId()).contains(motcle)).collect(Collectors.toList()));
        //COLLECT(collectors.toList()) converte le stream a une Liste 
        
        datatable.setItems(list);
        }
    }


    @FXML
    private void goToGestionMateriel(ActionEvent event) {
             try {
              //récupération fichier fxml
              
              FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVueAdministrateur.fxml"));
              //récupération du root  à partir du fichier fxml
              
              Parent root = loader.load();
              //récupération du controller lier au fichier fxml
              
              FXMLVueAdministrateurController dpc = loader.getController();
              //   dpc.setLbMessage(id_table.getSelectionModel().getSelectedItem().getId() + "");
              AjouterButton.getScene().setRoot(root);
          } catch (IOException ex) {
              Logger.getLogger(FXMLVueAdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        Categorie cat = (Categorie) datatable.getSelectionModel().getSelectedItem();
     ServiceMateriels ms = new ServiceMateriels();
     System.out.print(cat);
     ms.supprimer(cat.getId());
     showdata();
    }
        
        
    }
    
    

