package modele;

public class Bureau
{
    private int id;
    private String fonction;
    private String titre;
    private String nom;
    private String prenom;
    private String adresse;
    private String cp;
    private String ville;
    private String email;
    private String telPortable;

    public Bureau(String fonction, String titre, String nom, String prenom, String adresse, String cp, String ville, String email, String telPortable)
    {
        this.fonction = fonction;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.email = email;
        this.telPortable = telPortable;
    }

    public Bureau(int id, String fonction, String titre, String nom, String prenom, String adresse, String cp, String ville, String email, String telPortable)
    {
        this.id = id;
        this.fonction = fonction;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.email = email;
        this.telPortable = telPortable;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFonction()
    {
        return fonction;
    }

    public void setFonction(String fonction)
    {
        this.fonction = fonction;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTelPortable()
    {
        return telPortable;
    }

    public void setTelPortable(String telPortable)
    {
        this.telPortable = telPortable;
    }

    @Override
    public String toString()
    {
        return fonction;
    }

}
