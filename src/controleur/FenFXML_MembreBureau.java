package controleur;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Bureau;
import modele.GestionSql;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FenFXML_MembreBureau implements Initializable
{

    @FXML
    private TableView<Bureau> tableMembreBureau;
    @FXML
    private TableColumn<Bureau, String> tblCid;
    @FXML
    private TableColumn<Bureau, String> tblCfonction;
    @FXML
    private TableColumn<Bureau, String> tblCnom;
    @FXML
    private TableColumn<Bureau, String> tblCprenom;
    @FXML
    private TableColumn<Bureau, String> tblCadresse;
    @FXML
    private TableColumn<Bureau, String> tblCcp;
    @FXML
    private TableColumn<Bureau, String> tblCville;
    @FXML
    private TableColumn<Bureau, String> tblCemail;
    @FXML
    private TableColumn<Bureau, String> tblCportable;
    private ObservableList<Bureau> membresBureau;

    @FXML
    private Button btnModifBureau;
    @FXML
    private Button btnInsertBureau;
    @FXML
    private Button btnSupprBureau;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        membresBureau = GestionSql.getMembresBureau();

        if (membresBureau != null)
        {
            tblCid.setCellValueFactory(new PropertyValueFactory<>("id"));
            tblCfonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));
            tblCnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tblCprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tblCcp.setCellValueFactory(new PropertyValueFactory<>("cp"));
            tblCadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tblCville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            tblCemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tblCportable.setCellValueFactory(new PropertyValueFactory<>("telPortable"));
            tableMembreBureau.getItems().addAll(membresBureau);
        }
    }

    private void updateTable()
    {
        membresBureau.setAll(GestionSql.getMembresBureau());
    }

// In your FenFXML_MembreBureau class
    private void openModificationForm(Bureau selectedBureau)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/FenFXML_MembreBureauModif.fxml"));
            Parent root = loader.load();

            // Pass the selected Bureau to the ModificationFormController
            FenFXML_MembreBureauModifController controller = loader.getController();
            controller.setBureau(selectedBureau);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleModifierBureau()
    {
        Bureau selectedBureau = tableMembreBureau.getSelectionModel().getSelectedItem();
        if (selectedBureau != null)
        {
            openModificationForm(selectedBureau);
        }
    }

    // Fonction pour gérer l'événement du bouton "Insérer"
    private void openInsertionForm()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/FenFXML_MembreBureauInserer.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleInsererBureau()
    {
        openInsertionForm();
    }

    // Fonction pour gérer l'événement du bouton "Supprimer"
    @FXML
    private void handleSupprimerBureau()
    {
        Bureau selectedBureau = tableMembreBureau.getSelectionModel().getSelectedItem();
        if (selectedBureau != null)
        {
            // Appeler la fonction de suppression dans GestionSql
            GestionSql.deleteMembreBureau(selectedBureau.getId());
            // Mettre à jour le tableau
            updateTable();
        }
    }
}
