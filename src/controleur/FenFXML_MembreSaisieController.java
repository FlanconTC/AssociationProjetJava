package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.GestionSql;
import modele.Membre;

import java.sql.Date;
import modele.Cotiser;

public class FenFXML_MembreSaisieController
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
            Boolean recuEnvoye = false;

            Membre nouveauMembre = new Membre(titre, nom, prenom, adresse, cp, ville, pays, telFixe, telPortable, email);
            Cotiser nouvelleCotis = new Cotiser(0, nouveauMembre, dateVersement, cotisation, don, recuEnvoye);
            GestionSql.creeMembre(nouveauMembre,dateVersement, cotisation, don, recuEnvoye );

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
            System.out.println("Erreur de format : La cotisation doit être un nombre entier.");
        } catch (Exception e)
        {
            System.out.println("Erreur : Veuillez vérifier les champs obligatoires.");
        }
    }
}
