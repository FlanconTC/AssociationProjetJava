package controleur;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modele.*;

public class MainApp extends Application
{

    private Stage primaryStage;
    private Stage secondaryStage;

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        try
        {
            this.primaryStage.setTitle("Gestion de l'association");

            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_Menu.fxml"));

            AnchorPane rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e)
        {
            System.out.println("Erreur lors du chargement de FenFXML_Menu.fxml : " + e.getMessage());
            e.printStackTrace(); // Cela affichera la trace complète de l'exception dans la console.
        }
    }

    // Getter et Setter pour l'item selectionné dans le tableView des sessions (fenetre Inscription)
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
