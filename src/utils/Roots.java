/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Khalil ZRIBI
 */
public class Roots {

    public static void roots(String guifxmlLoginUserfxml, MouseEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    Parent root;
    Stage stage;
    Scene scene;

    Roots(String path,ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene); 
        stage.show();
    }

    public static void roots (String path,ActionEvent event) throws IOException {
        Roots r=new Roots(path,event);
    }
}
