package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Reservation implements Serializable {

    private int id;
    private Date dateDebut;
    private Date dateFin;
    private Chambre chambre;

    public Reservation(Date dateDebut, Date dateFin, Chambre chambre){
        Random random = new Random();
        this.id = random.nextInt();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.chambre = chambre;
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
}
