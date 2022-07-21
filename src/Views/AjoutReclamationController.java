/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import entities.Reclamation;
import entities.Typereclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import service.CrudReclamation;
import service.CrudTypeReclamation;

/**
 * FXML Controller class
 *
 * @author MALEK
 */
public class AjoutReclamationController implements Initializable {

    private TextField etat;
    @FXML
    private TextField desc;
    @FXML
    private ChoiceBox<Typereclamation> TypeField;
    CrudTypeReclamation cr = new CrudTypeReclamation();
    @FXML
    private Text textError;
    @FXML
    private Text succ;

     int idType;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cr.afficher().forEach((p) -> {
            TypeField.getItems().add(new Typereclamation(p.getId(), p.getType()));
            TypeField.setOnAction(event -> {
                idType = TypeField.getValue().getId();
                System.out.println(TypeField.getValue());
            });
        });

    }

    @FXML
    private void Submit(ActionEvent event) {

        if ( desc.getText().equals("") || TypeField.equals("")) {
            textError.setText("Tous les champs Sont obligatoire !!");
        } else {
            CrudReclamation crr = new CrudReclamation();
            String descript = crr.makeFine(desc.getText());

            crr.ajouter(new Reclamation(descript,"en cours de traitement" , idType));
            textError.setText("");
            succ.setText("Ajout Avec succes !!");
        }

    }

}