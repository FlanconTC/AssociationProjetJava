package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.GestionSql;
import modele.Membre;

import java.sql.Date;

public class FenFXML_MembreSaisieControleur
{

    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtPays;
    @FXML
    private DatePicker dpDateVersement;
    @FXML
    private TextField txtTelFixe;
    @FXML
    private TextField txtTelPortable;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCotisation;

    @FXML
    private Button btnEnregistrer;

    @FXML
    private void handleEnregistrer()
    {
        try
        {
            String titre = txtTitre.getText();
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String adresse = txtAdresse.getText();
            String cp = txtCodePostal.getText();
            String ville = txtVille.getText();
            String pays = txtPays.getText();
            Date dateVersement = Date.valueOf(dpDateVersement.getValue());
            String telFixe = txtTelFixe.getText();
            String telPortable = txtTelPortable.getText();
            String email = txtEmail.getText();
            int cotisation = Integer.parseInt(txtCotisation.getText());
            int don = 0;
            String recuEnvoye = "NON";

            // Créez un objet Membre avec les valeurs
            Membre nouveauMembre = new Membre(titre, nom, prenom, adresse, cp, ville, pays, dateVersement, telFixe, telPortable, email, cotisation, don, recuEnvoye);

            // Appelez la fonction d'insertion dans GestionSql
            GestionSql.creeMembre(nouveauMembre);

            // Réinitialisez les champs
            txtTitre.clear();
            txtNom.clear();
            txtPrenom.clear();
            txtAdresse.clear();
            txtCodePostal.clear();
            txtVille.clear();
            txtPays.clear();
            dpDateVersement.setValue(null);
            txtTelFixe.clear();
            txtTelPortable.clear();
            txtEmail.clear();
            txtCotisation.clear();
        } catch (NumberFormatException e)
        {
            // Handle the case where parsing a number fails
            System.out.println("Erreur de format : La cotisation doit être un nombre entier.");
        } catch (Exception e)
        {
            // Handle other exceptions or validation errors
            System.out.println("Erreur : Veuillez vérifier les champs obligatoires.");
        }
    }
}
