package main.java;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Reservation implements Serializable {

    private int id;
    private Date dateDebut;
    private Date dateFin;
    private Client client;
    private Chambre chambre;

    public Reservation(Date dateDebut, Date dateFin, Chambre chambre, Client client){
        Random random = new Random();
        this.id = random.nextInt();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.chambre = chambre;
        this.client = client;
    }


    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Reservation n°" + this.getId() + " pour la chambre n°" + this.chambre.getNumero() +
                " aux date " + this.dateDebut + " " + this.dateFin;
    }

    public Client getClient() {
        return client;
    }
}
