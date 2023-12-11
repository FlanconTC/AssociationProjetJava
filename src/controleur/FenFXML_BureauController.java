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

public class FenFXML_BureauController implements Initializable
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

    @FXML
    private void handleModifierBureau()
    {
        Bureau selectedBureau = tableMembreBureau.getSelectionModel().getSelectedItem();
        if (selectedBureau != null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/FenFXML_BureauModif.fxml"));
                Parent root = loader.load();

                FenFXML_BureauModifController controller = loader.getController();
                controller.setBureau(selectedBureau);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleInsererBureau()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/FenFXML_BureauInsert.fxml"));
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
    private void handleSupprimerBureau()
    {
        Bureau selectedBureau = tableMembreBureau.getSelectionModel().getSelectedItem();
        if (selectedBureau != null)
        {
            GestionSql.deleteMembreBureau(selectedBureau.getId());
            membresBureau.setAll(GestionSql.getMembresBureau());
        }
    }
}
