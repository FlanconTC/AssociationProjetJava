/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Bureau;
import modele.GestionSql;

/**
 * FXML Controller class
 *
 * @author Philippe
 */
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        membresBureau = GestionSql.getMembresBureau();

        if (membresBureau != null) {
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
    
   
}
