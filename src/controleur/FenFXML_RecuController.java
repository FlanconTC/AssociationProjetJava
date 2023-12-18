package controleur;

import java.util.ArrayList;
import javafx.collections.ListChangeListener;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import modele.Bureau;
import modele.GestionSql;
import modele.MembreSansRecu;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class FenFXML_RecuController implements Initializable
{

    @FXML
    private TableView<MembreSansRecu> tableMembresNonRecu;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCid;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCtitre;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCnom;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCprenom;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCadresse;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCcp;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCville;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCmail;
    @FXML
    private TableColumn<MembreSansRecu, String> tblCrecu;
    private ObservableList<MembreSansRecu> membresSansRecu;
    private ObservableList<Bureau> membresBureau;

    private List<MembreSansRecu> membresSelectionnes = new ArrayList<>();

    @FXML
    private ChoiceBox<Bureau> cbbxSignataire;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        membresBureau = GestionSql.getMembresBureau();
        cbbxSignataire.setItems(membresBureau);
        if (!membresBureau.isEmpty())
        {
            cbbxSignataire.setValue(membresBureau.get(0));
        }
        membresSansRecu = GestionSql.getMembresNonRecu();
        tblCid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCtitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tblCnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblCprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tblCadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tblCcp.setCellValueFactory(new PropertyValueFactory<>("CP"));
        tblCville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        tblCmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tblCrecu.setCellValueFactory(new PropertyValueFactory<>("recu"));
        tableMembresNonRecu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableMembresNonRecu.setItems(membresSansRecu);
        tableMembresNonRecu.getSelectionModel().getSelectedItems().addListener((ListChangeListener<MembreSansRecu>) change ->
        {
            membresSelectionnes.clear();
            membresSelectionnes.addAll(change.getList());
        });
    }

    public void btnRecuClic(ActionEvent event)
    {
        Bureau signataire = cbbxSignataire.getValue();
        if (!signataire.equals(null))
        {
            if (!membresSelectionnes.equals(null))
            {

                for (MembreSansRecu unMembre : membresSelectionnes)
                {
                    try
                    {
                        PDDocument document = new PDDocument();
                        PDPage page = new PDPage();
                        document.addPage(page);

                        try (PDPageContentStream contentStream = new PDPageContentStream(document, page))
                        {                 
                        float yStart = 725;
                        float yPosition = yStart;
                        contentStream.beginText();
                        contentStream.newLineAtOffset(30, yPosition);
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
                        contentStream.setLeading(14.5f);
                        contentStream.setFont(PDType1Font.TIMES_BOLD, 24);
                        contentStream.showText("                            RECU FISCAL 2023");
                        yPosition -= 15;
                        contentStream.newLine();
                        contentStream.newLine();
                        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
                        contentStream.showText("    Je soussigné.e " + signataire.getPrenom() + " " + signataire.getNom() + ", ");
                        contentStream.newLine();
                        contentStream.showText("    "+signataire.getFonction() + " de l'association AUTISME TREGOR GOELO");
                        contentStream.newLine();
                        contentStream.newLine();
                        contentStream.showText("                    "+unMembre.getAdresse());
                        contentStream.newLine();
                        contentStream.showText("                    "+unMembre.getCP());
                        contentStream.newLine();
                        contentStream.showText("                    "+unMembre.getVille());
                        contentStream.newLine();
                        yPosition -= 15;
                        contentStream.newLine();
                        contentStream.showText("    un don d'une valeur de " + unMembre.getDon() + "€ le " + unMembre.getDateVers().toString()+".");
                        yPosition -= 15;
                        contentStream.endText(); 
                        }                    
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setInitialFileName("Recu_ATG_" + unMembre.getPrenom() + unMembre.getNom() + unMembre.getVille() + ".pdf");
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));                   
                        File downloadDirectory = new File(System.getProperty("user.home") + "/Downloads");
                        fileChooser.setInitialDirectory(downloadDirectory);                     
                        Stage stage = (Stage) tableMembresNonRecu.getScene().getWindow();
                        File file = fileChooser.showSaveDialog(stage);
                        if (file != null)
                        {
                            document.save(file);
                            document.close();

                            Desktop.getDesktop().open(file);
                        }
                        String from = "philippe.logiou@orange.fr";
                        String password = "Dbrcecpldb2024!";

                        Properties props = new Properties();
                        String to = unMembre.getMail();
                        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.ssl.trust", "smtp.orange.fr");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.orange.fr");
                        props.put("mail.smtp.port", "587");
                        props.setProperty("mail.smtp.starttls.enable", "true");
                        Session session = Session.getInstance(props, new javax.mail.Authenticator()
                        {
                            protected PasswordAuthentication getPasswordAuthentication()
                            {
                                return new PasswordAuthentication(from, password);
                            }
                        });
                        try
                        {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("philippe.logiou@orange.fr"));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(unMembre.getMail()));
                            message.setSubject("Reçu don " + unMembre.getNom());
                            message.setText("Voici le reçu pour le don de : " + unMembre.getNom());
                            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                            attachmentBodyPart.attachFile(file);
                            Multipart multipart = new MimeMultipart();
                            multipart.addBodyPart(attachmentBodyPart);
                            message.setContent(multipart);
                            // Transport.send(message);  // comme d'hab ça marche pas #super :)) (firewall)
                        } catch (MessagingException e)
                        {
                            e.printStackTrace();
                        }
                        GestionSql.validerRecu(unMembre.getId(), unMembre.getDateVers(), unMembre.getCotisation());
                        stage.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
