package modele;

import java.sql.Date;

public class Membre
{

    private int id;
    private String titre;
    private String nom;
    private String prenom;
    private String adresse;
    private String cp;
    private String ville;
    private String pays;
    private String telFixe;
    private String telPortable;
    private String email;


    public Membre()
    {
    }

    public Membre(int id, String titre, String nom, String prenom, String adresse, String cp, String ville, String pays, String telFixe, String telPortable, String email)
    {
        this.id = id;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.telFixe = telFixe;
        this.telPortable = telPortable;
        this.email = email;
    }

    public Membre(String titre, String nom, String prenom, String adresse, String cp, String ville, String pays, String telFixe, String telPortable, String email)
    {
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.telFixe = telFixe;
        this.telPortable = telPortable;
        this.email = email;
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
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public String getCp()
    {
        return cp;
    }

    public void setCp(String cp)
    {
        this.cp = cp;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public String getPays()
    {
        return pays;
    }

    public void setPays(String pays)
    {
        this.pays = pays;
    }

    public String getTelFixe()
    {
        return telFixe;
    }

    public void setTelFixe(String telFixe)
    {
        this.telFixe = telFixe;
    }

    public String getTelPortable()
    {
        return telPortable;
    }

    public void setTelPortable(String telPortable)
    {
        this.telPortable = telPortable;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
