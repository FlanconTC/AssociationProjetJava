package modele;

import java.util.Date;

public class Cotiser
{

    private int id;
    private Membre idMembre;
    private Date dateVersement;
    private double cotisation;
    private double don;
    private String recuEmail;

    public Cotiser()
    {
    }

    public Cotiser(int id, Membre idMembre, Date dateVersement, double cotisation, double don, String recuEmail)
    {
        this.id = id;
        this.idMembre = idMembre;
        this.dateVersement = dateVersement;
        this.cotisation = cotisation;
        this.don = don;
        this.recuEmail = recuEmail;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Membre getIdMembre()
    {
        return idMembre;
    }

    public void setIdMembre(Membre idMembre)
    {
        this.idMembre = idMembre;
    }

    public Date getDateVersement()
    {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement)
    {
        this.dateVersement = dateVersement;
    }

    public double getCotisation()
    {
        return cotisation;
    }

    public void setCotisation(double cotisation)
    {
        this.cotisation = cotisation;
    }

    public double getDon()
    {
        return don;
    }

    public void setDon(double don)
    {
        this.don = don;
    }

    public String isRecuEmail()
    {
        return recuEmail;
    }

    public void setRecuEmail(String recuEmail)
    {
        this.recuEmail = recuEmail;
    }

}
