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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import service.ServiceUtilisateur;
import utils.Roots;

/**
 * FXML Controller class
 *
 * @author Khalil ZRIBI
 */
public class FXMLGestionUserController implements Initializable {

    @FXML
    private TextField Recherche;
    @FXML
    private TableView<Utilisateur> datatable;
    @FXML
    private TableColumn<Utilisateur, String> ClnNom;
    @FXML
    private TableColumn<Utilisateur, String> ClnPrenom;
    @FXML
    private TableColumn<Utilisateur, String> ClnEmail;
    @FXML
    private TableColumn<Utilisateur, Integer> ClnAge;
    @FXML
    private TableColumn<Utilisateur, Integer> ClnTel;
    @FXML
    private TableColumn<Utilisateur, String> ClnGenre;
    @FXML
    private TableColumn<Utilisateur, String> ClnType;
    @FXML
    private TableColumn<Utilisateur, String> ClnRole;
    @FXML
    private Button AjouterButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button modifierButton;
    @FXML
    private AnchorPane anchorForm;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtAge;
    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtTel;
    @FXML
    private Button save;
    @FXML
    private Button Annuler;
    
    private String formAction;
    private int idCatselected;
    @FXML
    private TextField TxtRole;
    @FXML
    private TextField TxtGenre;
    @FXML
    private TextField TxtType;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.formAction="ajouter";
        showdata();
        ServiceUtilisateur sc = new ServiceUtilisateur();
        //Utilisateur.getItems().addAll(sc.getTout());
        
        
        this.supprimerButton.setDisable(true);
        this.modifierButton.setDisable(true);
        this.anchorForm.setOpacity(0);
         ObservableList selectedCells = datatable.getSelectionModel().getSelectedCells(); // les lignes eli sar 3lahom el selection 
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Utilisateur u = (Utilisateur) datatable.getSelectionModel().getSelectedItem();
                if(u!=null){
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
        ServiceUtilisateur su = new ServiceUtilisateur();
         ObservableList<Utilisateur> list = FXCollections.observableArrayList();
          ClnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ClnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ClnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ClnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        ClnTel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        ClnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
          ClnType.setCellValueFactory(new PropertyValueFactory<>("type"));
          ClnRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        list.addAll(su.getTout());
        datatable.setItems(list);
        
    }

    @FXML
    private void ChercherUser(KeyEvent event) {
        if(this.Recherche.getText().compareTo("")==0){
            showdata();
        }else{
            String motcle=this.Recherche.getText();
            ServiceUtilisateur su = new ServiceUtilisateur();
         ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        ClnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ClnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ClnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ClnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        ClnTel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        ClnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ClnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ClnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
       // idCat.setCellValueFactory(new PropertyValueFactory<>("idCat"));

        list.addAll(su.getTout().stream().filter(c-> c.getType().contains(motcle)
                ||c.getGenre().contains(motcle)||c.getNom().contains(motcle)||c.getPrenom().contains(motcle)
                ||c.getRole().contains(motcle)).collect(Collectors.toList()));
        //COLLECT(collectors.toList()) converte le stream a une Liste 
        
        datatable.setItems(list);
        }
    }

    @FXML
    private void showFormAjouter(ActionEvent event) {
        this.formAction="ajouter";
                this.anchorForm.setOpacity(1);
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
        Utilisateur u = (Utilisateur) datatable.getSelectionModel().getSelectedItem();
     ServiceUtilisateur su = new ServiceUtilisateur();
     su.supprimer(u.getIdU());
     showdata();
    }

    @FXML
    private void showFormModifer(ActionEvent event) {
        this.formAction="modifier";
        this.anchorForm.setOpacity(1);
             Utilisateur u = (Utilisateur) datatable.getSelectionModel().getSelectedItem();
       this.TxtNom.setText(u.getNom());
       this.TxtPrenom.setText(u.getPrenom());
       this.TxtEmail.setText(u.getEmail());
       this.TxtAge.setText(String.valueOf(u.getAge()));
       this.TxtTel.setText(String.valueOf(u.getTelephone()));
       this.TxtGenre.setText(u.getGenre());
       this.TxtType.setText(u.getType());
       this.TxtRole.setText(u.getRole());
       this.save.setText("Modifier");
      // this.listecategorie.getSelectionModel().select(new Categorie(mat.getIdCat()));
    }

    @FXML
    private void Retour(ActionEvent event) {
        try{
                Roots.roots("../GUI/FXMLVueAdmin.fxml", event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
    }

    @FXML
    private void submitForm(ActionEvent event) {
        ServiceUtilisateur su = new ServiceUtilisateur();
     // this.idCatselected=listecategorie.getSelectionModel().getSelectedItem().getId();
      
      if(this.formAction.compareTo("ajouter")==0){
    //Float.valueof(string input ) : Float output 
    
    Utilisateur u = new Utilisateur(0,TxtNom.getText(),TxtPrenom.getText(),TxtEmail.getText() ,Integer.parseInt(TxtAge.getText()),Integer.parseInt(TxtTel.getText()), TxtGenre.getText(), TxtType.getText(), TxtRole.getText());

    su.ajouter(u);
                this.TxtNom.setText("");
                this.TxtPrenom.setText("");
                this.TxtEmail.setText("");
                this.TxtAge.setText("");
                this.TxtTel.setText("");
                this.TxtGenre.setText("");
                this.TxtType.setText("");
                this.TxtRole.setText("");
                Alert a = new Alert(Alert.AlertType.NONE);

                a.setAlertType(Alert.AlertType.INFORMATION);
 
                // set content text
                a.setContentText(" Utilisateur ajoutés");
 
                // show the dialog
                a.show();
         showdata();
         this.anchorForm.setOpacity(0);
      }else{
          //modifier
////               Utilisateur u = (Utilisateur) datatable.getSelectionModel().getSelectedItem();
////    Utilisateur u1 = new Utilisateur(u.getIdU(),TxtNom.getText(),TxtPrenom.getText(),TxtEmail.getText() ,Integer.parseInt(TxtAge.getText()),Integer.parseInt(TxtTel.getText()), TxtGenre.getText(), TxtType.getText(), TxtRole.getText());
////        su.modifier(u1);
////                this.TxtNom.setText("");
////                this.TxtPrenom.setText("");
////                this.TxtEmail.setText("");
////                this.TxtAge.setText("");
////                this.TxtTel.setText("");
////                this.TxtGenre.setText("");
////                this.TxtType.setText("");
////                this.TxtRole.setText("");

            int id = datatable.getSelectionModel().getSelectedItem().getIdU();
        Utilisateur user = new Utilisateur();

        user.setIdU(id);
        user.setNom(TxtNom.getText());
        user.setPrenom(TxtPrenom.getText());
        user.setEmail(TxtEmail.getText());
        user.setAge(Integer.parseInt(TxtAge.getText()));
        user.setTelephone(Integer.parseInt(TxtTel.getText()));
        user.setGenre(TxtGenre.getText());
        user.setType(TxtType.getText());
        user.setRole(TxtRole.getText());
        ServiceUtilisateur s = new ServiceUtilisateur();
        s.modifier(user);
        datatable.setItems(s.setuser(s.getTout()));
                
                Alert a = new Alert(Alert.AlertType.NONE);

                a.setAlertType(Alert.AlertType.INFORMATION);
 
                // set content text
                a.setContentText(" utilisateur Modifieé");
 
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
               this.TxtNom.setText("");
                this.TxtPrenom.setText("");
                this.TxtEmail.setText("");
                this.TxtAge.setText("");
                this.TxtTel.setText("");
                this.TxtGenre.setText("");
                this.TxtType.setText("");
                this.TxtRole.setText("");
    }  
    
    
}
