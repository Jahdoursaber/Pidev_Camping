/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import entities.Materiels;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import service.ServiceMateriels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import service.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLVueAdministrateurController implements Initializable {

    @FXML
    private Button AjouterButton;
    @FXML
    private Button supprimerButton;
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
    @FXML
    private TextField inputnom;
    @FXML
    private TextField Descriptioninput;
    @FXML
    private TextField referenceinput;
    @FXML
    private TextField etatinput;
    @FXML
    private TextField prixinput;
    @FXML
    private Button uploadfileinput;
    @FXML
    private TableView<Materiels> datatable;
    @FXML
    private TableColumn<Materiels, String> nomdata;
    @FXML
    private TableColumn<Materiels, String> descriptiondata;
    @FXML
    private TableColumn<Materiels, String> referencedata;
    @FXML
    private TableColumn<Materiels, String> etatdata;
    @FXML
    private TableColumn<Materiels, Float> prixdata;
    // il faut utiliser la class Float pas float type primitif .
    @FXML
    private TableColumn<Materiels, String> photodata;
    private String formAction;
    @FXML
    private TextField chercherInput;
    @FXML
    private ComboBox<Categorie> listecategorie;
    private int idCatselected;
    @FXML
    private TableColumn<Materiels,Integer> idCat;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.formAction="ajouter";
        showdata();
        ServiceCategorie sc = new ServiceCategorie();
        listecategorie.getItems().addAll(sc.getTout());
        
        
        this.supprimerButton.setDisable(true);
        this.modifierButton.setDisable(true);
        this.anchorForm.setOpacity(0);
         ObservableList selectedCells = datatable.getSelectionModel().getSelectedCells(); // les lignes eli sar 3lahom el selection 
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                Materiels mat = (Materiels) datatable.getSelectionModel().getSelectedItem();
                if(mat!=null){
                   supprimerButton.setDisable(false);
                   modifierButton.setDisable(false);
                }else{
                      supprimerButton.setDisable(true);
                   modifierButton.setDisable(true);
                }
            }
           
        });
    }    
    public void showdata(){
        ServiceMateriels sm = new ServiceMateriels();
         ObservableList<Materiels> list = FXCollections.observableArrayList();
          nomdata.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptiondata.setCellValueFactory(new PropertyValueFactory<>("description"));
        referencedata.setCellValueFactory(new PropertyValueFactory<>("reference"));
        etatdata.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixdata.setCellValueFactory(new PropertyValueFactory<>("prix"));
        photodata.setCellValueFactory(new PropertyValueFactory<>("photo"));
          idCat.setCellValueFactory(new PropertyValueFactory<>("idCat"));

        list.addAll(sm.getTout());
        datatable.setItems(list);
        
    }


    @FXML
    private void submitForm(ActionEvent event) {
                ServiceMateriels sm = new ServiceMateriels();
      this.idCatselected=listecategorie.getSelectionModel().getSelectedItem().getId();
      
      if(this.formAction.compareTo("ajouter")==0){
    //Float.valueof(string input ) : Float output 
    
        Materiels m = new Materiels(0,this.inputnom.getText(),this.Descriptioninput.getText(), this.referenceinput.getText(),this.fileName.getText(),this.etatinput.getText(), 
                Float.valueOf(this.prixinput.getText()),this.idCatselected); 
        sm.ajouter(m);
           this.inputnom.setText("");
                this.Descriptioninput.setText("");
                this.referenceinput.setText("");
                this.etatinput.setText("");
                this.prixinput.setText("");
                Alert a = new Alert(AlertType.NONE);

                a.setAlertType(AlertType.INFORMATION);
 
                // set content text
                a.setContentText(" Materiels ajoutés");
 
                // show the dialog
                a.show();
         showdata();
         this.anchorForm.setOpacity(0);
      }else{
          //modifier
               Materiels mat = (Materiels) datatable.getSelectionModel().getSelectedItem();
        Materiels m1 = new Materiels(mat.getId(),this.inputnom.getText(),this.Descriptioninput.getText(), this.referenceinput.getText(),this.fileName.getText(),this.etatinput.getText(), 
        Float.valueOf(this.prixinput.getText()),this.idCatselected); 
        sm.modifier(m1);
           this.inputnom.setText("");
                this.Descriptioninput.setText("");
                this.referenceinput.setText("");
                this.etatinput.setText("");
                this.prixinput.setText("");
                Alert a = new Alert(AlertType.NONE);

                a.setAlertType(AlertType.INFORMATION);
 
                // set content text
                a.setContentText(" Materiels Modifieé");
 
                // show the dialog
                a.show();
         showdata();
         this.save.setText("Ajouter");
         this.anchorForm.setOpacity(0);

        
    
        
      }
         
          
       
        
    }

    @FXML
    private void CloseForm(ActionEvent event) {
                //Init Form
                this.anchorForm.setOpacity(0);
                this.inputnom.setText("");
                this.Descriptioninput.setText("");
                this.referenceinput.setText("");
                this.etatinput.setText("");
                this.prixinput.setText("");

    }

    @FXML
    private void supprimerMat(ActionEvent event) {
     Materiels mat = (Materiels) datatable.getSelectionModel().getSelectedItem();
     ServiceMateriels ms = new ServiceMateriels();
     ms.supprimer(mat.getId());
     showdata();
     
     

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
             Materiels mat = (Materiels) datatable.getSelectionModel().getSelectedItem();
       this.inputnom.setText(mat.getNom());
       this.Descriptioninput.setText(mat.getDescription());
       this.etatinput.setText(mat.getEtat());
       this.referenceinput.setText(mat.getReference());
       this.prixinput.setText(String.valueOf(mat.getPrix()));
       this.fileName.setText(mat.getPhoto());
       this.save.setText("Modifier");
       this.listecategorie.getSelectionModel().select(new Categorie(mat.getIdCat()));
        
        
    }

    @FXML
    private void inputFile(ActionEvent event) {
        String dest ="C:\\Users\\Dell\\Documents\\NetBeansProjects\\Pidev_Camping\\src\\GUI\\image\\";

        Path to;
        Path from;
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        
        this.fileName.setText(file.getName());
        to = Paths.get(dest + file.getName());
        from = Paths.get(file.toURI());
        try{
        Files.copy(from, to);
        }catch(IOException ex){
            System.out.print(ex.getMessage());
        }
        
        
        
        

        
    }

    @FXML
    private void ChercherMat(KeyEvent event) {
        if(this.chercherInput.getText().compareTo("")==0){
            showdata();
        }else{
            String motcle=this.chercherInput.getText();
            ServiceMateriels sm = new ServiceMateriels();
         ObservableList<Materiels> list = FXCollections.observableArrayList();
          nomdata.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptiondata.setCellValueFactory(new PropertyValueFactory<>("description"));
        referencedata.setCellValueFactory(new PropertyValueFactory<>("reference"));
        etatdata.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixdata.setCellValueFactory(new PropertyValueFactory<>("prix"));
        photodata.setCellValueFactory(new PropertyValueFactory<>("photo"));
                  idCat.setCellValueFactory(new PropertyValueFactory<>("idCat"));

        list.addAll(sm.getTout().stream().filter(c-> c.getDescription().contains(motcle)
                ||c.getEtat().contains(motcle)||c.getNom().contains(motcle)||String.valueOf(c.getPrix()).contains(motcle)).collect(Collectors.toList()));
        //COLLECT(collectors.toList()) converte le stream a une Liste 
        
        datatable.setItems(list);
        }
        
        
    }

    @FXML
    private void gotoGestionCategorie(ActionEvent event) {
          try {
              //récupération fichier fxml
              
              FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVueAdminstrateurCategorie.fxml"));
              //récupération du root  à partir du fichier fxml
              
              Parent root = loader.load();
              //récupération du controller lier au fichier fxml
              
              FXMLVueAdminstrateurCategorieController dpc = loader.getController();
              AjouterButton.getScene().setRoot(root);
          } catch (IOException ex) {
              Logger.getLogger(FXMLVueAdminstrateurCategorieController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
    
}
