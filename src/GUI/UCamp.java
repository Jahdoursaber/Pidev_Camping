/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class UCamp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         Parent root;
        try {
           root = FXMLLoader.load(getClass().getResource("FXMLVueAdministrateur.fxml"));
         //  root = FXMLLoader.load(getClass().getResource("FXMLVueClient.fxml"));
           Scene scene = new Scene(root);
            primaryStage.setTitle("U-CAMP");
            primaryStage.setScene(scene);
            primaryStage.show();
          } catch (IOException ex) {
            Logger.getLogger(UCamp.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
