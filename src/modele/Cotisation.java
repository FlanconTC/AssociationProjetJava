package modele;

public class Cotisation
{

    private double montant;

    public Cotisation()
    {
    }

    public Cotisation(double montant)
    {
        this.montant = montant;
    }

    public double getMontant()
    {
        return montant;
    }

    public void setMontant(double montant)
    {
        this.montant = montant;
    }
}
