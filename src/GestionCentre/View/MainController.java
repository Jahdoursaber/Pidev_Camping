/*S
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCentre.View;

import gestioncentre.Entites.Centre;
import gestioncentre.Service.CrudCentre;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author mbraiki
 */
public class MainController implements Initializable {

    @FXML
    private TableView<Centre> tabU;
    @FXML
    private TableColumn<Centre, Integer> colid;
    @FXML
    private TableColumn<Centre, String> colName;
    @FXML
    private TableColumn<Centre, String> coldescription;
    @FXML
    private TableColumn<Centre, String> colphoto;
    @FXML
    private TableColumn<Centre, String> coladdresse;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtdescription;
    private TextField txtphoto;
    @FXML
    private TextField txtaddresse;
    @FXML
    private Button btnA;
    @FXML
    private Button btnM;
    @FXML
    private Button btnS;
    @FXML
    private Button uploadfileinput;
    @FXML
    private Text fileName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       showCentre();
    }    
    private void showCentre() {
        CrudCentre se = new CrudCentre();
        for (Centre e : se.getTout()) {
            ObservableList<Centre> list = se.getTout();

            colid.setCellValueFactory(new PropertyValueFactory<Centre, Integer>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<Centre, String>("Name"));
            coldescription.setCellValueFactory(new PropertyValueFactory<Centre, String>("description"));
            colphoto.setCellValueFactory(new PropertyValueFactory<Centre, String>("photo"));
            coladdresse.setCellValueFactory(new PropertyValueFactory<Centre, String>("addresse"));
            
            tabU.setItems(list);
        }

        //se.getAll();  
    }
    @FXML
    private void ajouter(ActionEvent event) {
         Centre e = new Centre(txtName.getText(), txtdescription.getText(), uploadfileinput.getText(), txtaddresse.getText());

        CrudCentre se = new CrudCentre();

        se.add(e);
        txtid.setText("");
        txtName.setText("");
        txtdescription.setText("");
        uploadfileinput.setText("");
        txtaddresse.setText("");
        showCentre();
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            CrudCentre se = new CrudCentre();
            Centre e = se.getById(parseInt(txtid.getText()));
           
            
              
            
            e.setId(parseInt(txtid.getText()));          
            e.setName(txtName.getText());
            e.setDescription(txtdescription.getText());
            e.setPhoto(uploadfileinput.getText());
            e.setAddresse(txtaddresse.getText());
            
            se.update(e);
            showCentre();
            
            
            
            System.out.println("modification avec succee");
        } catch (SQLException ex) {
            System.out.println("erreur de connexion" + ex);
        }
        showCentre();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        try {
            CrudCentre se = new CrudCentre();
            Centre e = se.getById(parseInt(txtid.getText()));
          
            se.delete(e);
            System.out.println("supprission avec succee");
        } catch (SQLException ex) {
            System.out.println("erreur de connexion" + ex);
        }
       showCentre();
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
    
}
