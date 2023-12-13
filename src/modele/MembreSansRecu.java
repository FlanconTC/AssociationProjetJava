package modele;

import java.sql.Date;

public class MembreSansRecu
{
    private int id;
    private String Titre, Nom, Prenom, Adresse, CP, Ville, Mail;
    private int cotisation;
    private Date dateVers;
    private String recu;

    public MembreSansRecu(int id, String Titre, String Nom, String Prenom, String Adresse, String CP, String Ville, String Mail, int cotisation, Date dateVers, String recu)
    {
        this.id = id;
        this.Titre = Titre;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adresse = Adresse;
        this.CP = CP;
        this.Ville = Ville;
        this.Mail = Mail;
        this.cotisation = cotisation;
        this.dateVers = dateVers;
        this.recu = recu;
    }

    public int getCotisation()
    {
        return cotisation;
    }

    public void setCotisation(int cotisation)
    {
        this.cotisation = cotisation;
    }

    public Date getDateVers()
    {
        return dateVers;
    }

    public void setDateVers(Date dateVers)
    {
        this.dateVers = dateVers;
    }
    

   

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitre()
    {
        return Titre;
    }

    public void setTitre(String Titre)
    {
        this.Titre = Titre;
    }

    public String getNom()
    {
        return Nom;
    }

    public void setNom(String Nom)
    {
        this.Nom = Nom;
    }

    public String getPrenom()
    {
        return Prenom;
    }

    public void setPrenom(String Prenom)
    {
        this.Prenom = Prenom;
    }

    public String getAdresse()
    {
        return Adresse;
    }

    public void setAdresse(String Adresse)
    {
        this.Adresse = Adresse;
    }

    public String getCP()
    {
        return CP;
    }

    public void setCP(String CP)
    {
        this.CP = CP;
    }

    public String getVille()
    {
        return Ville;
    }

    public void setVille(String Ville)
    {
        this.Ville = Ville;
    }

    public String getMail()
    {
        return Mail;
    }

    public void setMail(String Mail)
    {
        this.Mail = Mail;
    }

    public String getRecu()
    {
        return recu;
    }

    public void setRecu(String recu)
    {
        this.recu = recu;
    }
    
}
