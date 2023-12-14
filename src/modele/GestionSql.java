package modele;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class GestionSql
{
    private static final String url = "jdbc:mysql://localhost/association?characterEncoding=UTF8";

    public static ObservableList<Bureau> getMembresBureau()
    {
        ObservableList<Bureau> lesMembres = FXCollections.observableArrayList();
        String req = "SELECT b.id, b.fonction, b.titre, b.nom, b.prenom, b.adresse, b.cp, b.ville, b.email, b.telPortable FROM bureau b;";

        try (Connection conn = DriverManager.getConnection(url, "root", ""); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(req))
        {

            while (rs.next())
            {
                Bureau membreBureau = new Bureau(
                        rs.getInt("id"),
                        rs.getString("fonction"),
                        rs.getString("titre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("cp"),
                        rs.getString("ville"),
                        rs.getString("email"),
                        rs.getString("telPortable")
                );
                lesMembres.add(membreBureau);
            }
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
        return lesMembres;
    }

    public static ObservableList<Membre> getMembres()
    {
        ObservableList<Membre> lesMembres = FXCollections.observableArrayList();
        String req = "SELECT m.id, m.titre, m.nom, m.prenom, m.adresse, m.cp, m.ville, m.pays, m.telFixe, m.telPortable, m.email FROM membres m;";

        try (Connection conn = DriverManager.getConnection(url, "root", ""); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(req))
        {

            while (rs.next())
            {
                Membre membre = new Membre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("cp"),
                        rs.getString("ville"),
                        rs.getString("pays"),
                        rs.getString("telFixe"),
                        rs.getString("telPortable"),
                        rs.getString("email")
                );
                lesMembres.add(membre);
            }
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
        return lesMembres;
    }

    public static void modifierMembreBureau(Bureau membreBureau)
    {
        String req = "UPDATE bureau SET fonction=?, titre=?, nom=?, prenom=?, adresse=?, cp=?, ville=?, email=?, telPortable=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, "root", ""); PreparedStatement pstmt = conn.prepareStatement(req))
        {

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
                showAlert("Succès", "Modification effectuée avec succès.");
            } else
            {
                showAlert("Échec", "La modification a échoué.");
            }
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public static void insertMembreBureau(Bureau membreBureau)
    {
        String req = "INSERT INTO bureau (fonction, titre, nom, prenom, adresse, cp, ville, email, telPortable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, "root", ""); PreparedStatement pstmt = conn.prepareStatement(req))
        {

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
                showAlert("Succès", "Insertion effectuée avec succès.");
            } else
            {
                showAlert("Échec", "L'insertion a échoué.");
            }
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public static void deleteMembreBureau(int idMembre)
    {
        String req = "DELETE FROM bureau WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, "root", ""); PreparedStatement pstmt = conn.prepareStatement(req))
        {
            pstmt.setInt(1, idMembre);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0)
            {
                showAlert("Succès", "Membre du bureau supprimé avec succès.");
            } else
            {
                showAlert("Échec", "La suppression du membre du bureau a échoué.");
            }
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public static void creeMembre(Membre membre, Date dateVers, int cotis, int don, String recept)
    {
        try (Connection conn = DriverManager.getConnection(url, "root", ""))
        {
            conn.setAutoCommit(false);

            int memberId = insertMembre(conn, membre);
            if (memberId > 0)
            {
                adjustCotisationAndDon(conn, memberId, dateVers, cotis, don, recept);
                conn.commit();
                showAlert("Échec", "Creation du membre reussi.");
            } else
            {
                conn.rollback();
                showAlert("Échec", "L'enregistrement du membre a échoué.");
            }
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    private static int insertMembre(Connection conn, Membre membre) throws SQLException
    {
        String insertMembreQuery = "INSERT INTO membres (titre, nom, prenom, adresse, cp, ville, pays, telFixe, telPortable, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertMembreQuery, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setString(1, membre.getTitre());
            pstmt.setString(2, membre.getNom());
            pstmt.setString(3, membre.getPrenom());
            pstmt.setString(4, membre.getAdresse());
            pstmt.setString(5, membre.getCp());
            pstmt.setString(6, membre.getVille());
            pstmt.setString(7, membre.getPays());
            pstmt.setString(8, membre.getTelFixe());
            pstmt.setString(9, membre.getTelPortable());
            pstmt.setString(10, membre.getEmail());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0)
            {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys())
                {
                    if (generatedKeys.next())
                    {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }
        return -1;
    }

    private static void adjustCotisationAndDon(Connection conn, int memberId, Date dateVers, int cotis, int don, String recept) throws SQLException
    {
        double montantCotisation = getMontantCotisation(conn);
        if (cotis > montantCotisation)
        {
            don += cotis - montantCotisation;
            cotis = (int) montantCotisation;
        }

        String insertCotiserQuery = "INSERT INTO cotiser (idMembre, dateVersement, cotisation, don, recuEmail) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertCotiserQuery))
        {
            pstmt.setInt(1, memberId);
            pstmt.setDate(2, dateVers);
            pstmt.setInt(3, cotis);
            pstmt.setInt(4, don);
            pstmt.setString(5, recept);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0)
            {
                throw new SQLException("L'insertion des données de cotisation a échoué.");
            }
        }
    }

    private static double getMontantCotisation(Connection conn) throws SQLException
    {
        String cotisationCheckQuery = "SELECT montant FROM cotisation";
        try (PreparedStatement pstmt = conn.prepareStatement(cotisationCheckQuery); ResultSet cotisationResult = pstmt.executeQuery())
        {
            if (cotisationResult.next())
            {
                return cotisationResult.getDouble("montant");
            } else
            {
                return 0;
            }
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

    public static ObservableList<MembreSansRecu> getMembresNonRecu()
    {
        ObservableList<MembreSansRecu> lesMembresSansRecu = FXCollections.observableArrayList();
        String req = "SELECT m.id, m.titre, m.nom, m.prenom, m.adresse, m.cp, m.ville, m.email, c.cotisation, c.dateVersement, c.recuEmail FROM membres m, cotiser c WHERE m.id = c.idMembre AND c.recuEmail = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", ""); PreparedStatement pstmt = conn.prepareStatement(req))
        {

            pstmt.setString(1, "NON");
            try (ResultSet resultSet = pstmt.executeQuery())
            {
                while (resultSet.next())
                {
                    MembreSansRecu membreSansRecu = new MembreSansRecu(
                            resultSet.getInt("id"),
                            resultSet.getString("titre"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("adresse"),
                            resultSet.getString("CP"),
                            resultSet.getString("ville"),
                            resultSet.getString("email"),
                            resultSet.getInt("cotisation"),
                            resultSet.getDate("dateVersement"),
                            resultSet.getString("recuEmail")
                    );
                    lesMembresSansRecu.add(membreSansRecu);
                }
            }

        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
        return lesMembresSansRecu;
    }

    public static void validerRecu(int id, Date dateVers, int cotis)
    {
        String updateRecuEmailQuery = "UPDATE cotiser SET recuEmail = 'OUI' WHERE idMembre = ? AND dateVersement = ?";
        try (Connection conn = DriverManager.getConnection(url, "root", ""); PreparedStatement pstmt = conn.prepareStatement(updateRecuEmailQuery))
        {

            pstmt.setInt(1, id);
            pstmt.setDate(2, dateVers);

            pstmt.executeUpdate();
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public static void insererDon(int idMembre, String montantDon)
    {
        String req = "INSERT INTO cotiser (idMembre, dateVersement, cotisation, don, recuEmail) VALUES (?, NOW(), 0, ?, 'NON');";
        try (Connection conn = DriverManager.getConnection(url, "root", ""); PreparedStatement pstmt = conn.prepareStatement(req))
        {

            pstmt.setInt(1, idMembre);
            pstmt.setString(2, montantDon);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0)
            {
                showAlert("Succès", "Don enregistré avec succès.");
            } else
            {
                showAlert("Échec", "Le don a échoué.");
            }
        } catch (SQLException se)
        {
            Logger.getLogger(GestionSql.class.getName()).log(Level.SEVERE, null, se);
        }
    }
}
