package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controleur.MainApp;
import modele.*;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FenFXML_MenuControleur implements Initializable
{

    private Stage secondaryStage;
    @FXML
    private Button btnMembres;

    @FXML
    private Button btnBureau;

    @FXML
    private Button btnRecu;

    /**
     * Initializes the controller class.
     */
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
            loader.setLocation(getClass().getResource("/vue/FenFXML_MembreBureau.fxml"));
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
    private void lancerFenetreMembres()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/FenFXML_MembreSaisie.fxml"));
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

    }

}
