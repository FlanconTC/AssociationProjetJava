/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.GestionSql;
import modele.Membre;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FenFXML_DonController implements Initializable
{

    @FXML
    private ComboBox<Membre> comboMembres;

    @FXML
    private TextField txtDon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList<Membre> membres = GestionSql.getMembres();
        comboMembres.setItems(membres);

        comboMembres.setCellFactory(cell -> new ListCell<Membre>()
        {
            @Override
            protected void updateItem(Membre item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty || item == null)
                {
                    setText(null);
                } else
                {
                    setText(item.getNom() + " " + item.getPrenom());
                }
            }
        });

        comboMembres.setButtonCell(new ListCell<Membre>()
        {
            @Override
            protected void updateItem(Membre item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty || item == null)
                {
                    setText(null);
                } else
                {
                    setText(item.getNom() + " " + item.getPrenom());
                }
            }
        });
    }

    @FXML
    private void handleDonAction()
    {
        Membre membreSelectionne = comboMembres.getSelectionModel().getSelectedItem();
        String montantDon = txtDon.getText();

        if (membreSelectionne != null && montantDon != null)
        {
            GestionSql.insererDon(membreSelectionne.getId(), montantDon);
        }
    }
}
