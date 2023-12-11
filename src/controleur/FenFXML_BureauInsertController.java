package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Bureau;
import modele.GestionSql;

public class FenFXML_BureauInsertController
{

    @FXML
    private TextField txtFonction;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtCP;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelPortable;

    @FXML
    private void handleEnregistrer()
    {
        try
        {
            String fonction = txtFonction.getText();
            String titre = txtTitre.getText();
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String adresse = txtAdresse.getText();
            String cp = txtCP.getText();
            String ville = txtVille.getText();
            String email = txtEmail.getText();
            String telPortable = txtTelPortable.getText();

            Bureau nouveauMembreBureau = new Bureau(fonction, titre, nom, prenom, adresse, cp, ville, email, telPortable);

            GestionSql.insertMembreBureau(nouveauMembreBureau);
        } catch (Exception e)
        {
            System.out.println("Erreur : Veuillez vérifier les champs obligatoires.");
        }
    }

    @FXML
    private void handleAnnuler()
    {
        Stage stage = (Stage) txtFonction.getScene().getWindow();
        stage.close();
    }
}
