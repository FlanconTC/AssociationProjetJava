package controleur;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
