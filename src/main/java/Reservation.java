package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation implements Serializable {
    private Date dateDebut;
    private Date dateFin;
    private Chambre chambre;

    public Reservation(Date dateDebut, Date dateFin, Chambre chambre){
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.chambre = chambre;
    }


}
