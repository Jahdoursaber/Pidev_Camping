/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENEVO
 */
public class Camping extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
//      FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AjoutReclamation.fxml"));
//   // FXMLLoader loader = new FXMLLoader(getClass().getResource("../projet/views/Affichereclamation.fxml"));
// 
//       Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Ajouter Reclamation");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        primaryStage.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/AjoutType.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("AfficheType");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
