package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import javafx.scene.control.Alert;

public class GestionSql
{

    static String pilote = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/association?characterEncoding=UTF8";

    public static ObservableList<Bureau> getMembresBureau()
    {
        Connection conn;
        Statement stmt1;
        Bureau membreBureau;
        ObservableList<Bureau> lesMembres = FXCollections.observableArrayList();
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url, "root", "");
            stmt1 = conn.createStatement();

            String req = "select b.id, b.fonction, b.titre, b.nom, b.prenom, b.adresse, b.cp, b.ville, b.email, b.telPortable from bureau b;";
            ResultSet rs = stmt1.executeQuery(req);
            while (rs.next())
            {
                membreBureau = new Bureau(rs.getInt("id"), rs.getString("fonction"), rs.getString("titre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("cp"), rs.getString("ville"), rs.getString("email"), rs.getString("telPortable"));
                lesMembres.add(membreBureau);
            }
        } catch (ClassNotFoundException cnfe)
        {
            System.out.println("Erreur chargement driver getLesClients : " + cnfe.getMessage());
        } catch (SQLException se)
        {
            System.out.println("Erreur SQL requete getLesClients : " + se.getMessage());
        }
        return lesMembres;
    }

    public static void modifierMembreBureau(Bureau membreBureau)
    {
        try
        {
            Connection conn = DriverManager.getConnection(url, "root", "");
            String req = "UPDATE bureau SET fonction=?, titre=?, nom=?, prenom=?, adresse=?, cp=?, ville=?, "
                    + "email=?, telPortable=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(req);

            pstmt.setString(1, membreBureau.getFonction());
            pstmt.setString(2, membreBureau.getTitre());
            pstmt.setString(3, membreBureau.getNom());
            pstmt.setString(4, membreBureau.getPrenom());
            pstmt.setString(5, membreBureau.getAdresse());
            pstmt.setString(6, membreBureau.getCp());
            pstmt.setString(7, membreBureau.getVille());
            pstmt.setString(8, membreBureau.getEmail());
            pstmt.setString(9, membreBureau.getTelPortable());
            pstmt.setInt(10, membreBureau.getId());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0)
            {
                showAlert("Succès", "Modification effectuer avec succès.");
            } else
            {
                showAlert("Échec", "Modification échoué.");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException se)
        {
            System.out.println("Erreur SQL modification membre du bureau : " + se.getMessage());
        }
    }

    public static void insertMembreBureau(Bureau membreBureau)
    {
        try
        {
            Connection conn = DriverManager.getConnection(url, "root", "");
            String req = "INSERT INTO bureau (fonction, titre, nom, prenom, adresse, cp, ville, email, telPortable) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(req);

            pstmt.setString(1, membreBureau.getFonction());
            pstmt.setString(2, membreBureau.getTitre());
            pstmt.setString(3, membreBureau.getNom());
            pstmt.setString(4, membreBureau.getPrenom());
            pstmt.setString(5, membreBureau.getAdresse());
            pstmt.setString(6, membreBureau.getCp());
            pstmt.setString(7, membreBureau.getVille());
            pstmt.setString(8, membreBureau.getEmail());
            pstmt.setString(9, membreBureau.getTelPortable());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0)
            {
                showAlert("Succès", "Insertion effectuer avec succès.");
            } else
            {
                showAlert("Échec", "Insertion échoué.");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException se)
        {
            System.out.println("Erreur SQL insertion membre du bureau : " + se.getMessage());
        }
    }

    public static void deleteMembreBureau(int idMembre)
    {
        try
        {
            Connection conn = DriverManager.getConnection(url, "root", "");
            String req = "DELETE FROM bureau WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, idMembre);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0)
            {
                showAlert("Succès", "Supprimer avec succès.");
            } else
            {
                showAlert("Échec", "La suppression a échoué.");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException se)
        {
            System.out.println("Erreur SQL suppression membre : " + se.getMessage());
        }
    }

    public static void creeMembre(Membre membre)
    {
        try
        {
            Connection conn = DriverManager.getConnection(url, "root", "");
            String req = "INSERT INTO membres (titre, nom, prenom, adresse, cp, ville, pays, dateVersement, "
                    + "telFixe, telPortable, email, cotisation, don, recuEnvoye) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(req);

            pstmt.setString(1, membre.getTitre());
            pstmt.setString(2, membre.getNom());
            pstmt.setString(3, membre.getPrenom());
            pstmt.setString(4, membre.getAdresse());
            pstmt.setString(5, membre.getCp());
            pstmt.setString(6, membre.getVille());
            pstmt.setString(7, membre.getPays());
            pstmt.setDate(8, membre.getDateVersement());
            pstmt.setString(9, membre.getTelFixe());
            pstmt.setString(10, membre.getTelPortable());
            pstmt.setString(11, membre.getEmail());
            pstmt.setInt(12, membre.getCotisation());
            pstmt.setInt(13, membre.getDon());
            pstmt.setString(14, membre.getRecuEnvoye());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0)
            {
                showAlert("Succès", "Membre enregistré avec succès.");
            } else
            {
                showAlert("Échec", "L'enregistrement du membre a échoué.");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException se)
        {
            System.out.println("Erreur SQL insertion membre : " + se.getMessage());
        }
    }

    private static void showAlert(String title, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
