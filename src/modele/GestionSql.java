/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionSql
{
    static String pilote = "com.mysql.cj.jdbc.Driver"; // pb ici avec le driver 
    static String url = new String("jdbc:mysql://localhost/association?characterEncoding=UTF8");
    //Requete permettant de retourner l'ensemble des clients
    public static ObservableList<Bureau> getMembresBureau()
    {
        Connection conn;
        Statement stmt1;
        Bureau membreBureau;
        ObservableList<Bureau> lesMembres = FXCollections.observableArrayList();
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt1 = conn.createStatement();

            String req = "select b.id, b.fonction, b.titre, b.nom, b.prenom, b.adresse, b.cp, b.ville, b.email, b.telPortable from bureau b;";
            ResultSet rs = stmt1.executeQuery(req);
            while (rs.next())
            {
                membreBureau = new Bureau(rs.getInt("id"), rs.getString("fonction"), rs.getString("titre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("cp"), rs.getString("ville"), rs.getString("email"), rs.getString("telPortable"));
                lesMembres.add(membreBureau);
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Erreur chargement driver getLesClients : " + cnfe.getMessage());
        }
        catch (SQLException se)
        {
            System.out.println("Erreur SQL requete getLesClients : " + se.getMessage());
        }
        return lesMembres;
    }
    
}
