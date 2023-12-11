package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modele.Bureau;
import modele.GestionSql;

public class FenFXML_BureauModifController
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

    private Bureau bureau;

    public void setBureau(Bureau bureau)
    {
        this.bureau = bureau;
        if (bureau != null)
        {
            txtFonction.setText(bureau.getFonction());
            txtTitre.setText(bureau.getTitre());
            txtNom.setText(bureau.getNom());
            txtPrenom.setText(bureau.getPrenom());
            txtAdresse.setText(bureau.getAdresse());
            txtCP.setText(bureau.getCp());
            txtVille.setText(bureau.getVille());
            txtEmail.setText(bureau.getEmail());
            txtTelPortable.setText(bureau.getTelPortable());
        }
    }

    @FXML
    private void handleModifier()
    {
        if (bureau != null)
        {
            bureau.setFonction(txtFonction.getText());
            bureau.setTitre(txtTitre.getText());
            bureau.setNom(txtNom.getText());
            bureau.setPrenom(txtPrenom.getText());
            bureau.setAdresse(txtAdresse.getText());
            bureau.setCp(txtCP.getText());
            bureau.setVille(txtVille.getText());
            bureau.setEmail(txtEmail.getText());
            bureau.setTelPortable(txtTelPortable.getText());

            GestionSql.modifierMembreBureau(bureau);
        }
    }
}
