package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FenFXML_MenuController implements Initializable
{
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    @FXML
    private void lancerFenetreBureau()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/FenFXML_Bureau.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            Stage newStage = new Stage();
            newStage.setTitle("Écran gestion bureau");
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void lancerFenetreMembres()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/FenFXML_Membre.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            Stage newStage = new Stage();
            newStage.setTitle("Écran gestion membres");
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void lancerFenetreRecu()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/FenFXML_Recu.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            Stage newStage = new Stage();
            newStage.setTitle("Écran gestion reçus");
            newStage.setScene(scene);
            newStage.show();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
