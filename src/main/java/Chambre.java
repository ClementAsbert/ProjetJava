package main.java;

import main.java.Enum.Detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Chambre implements Serializable {
    private static final long serialVersionUID = 3542636389129079000L;
    private int numero;
    private boolean disponible;
    private Detail detail;

    //private List<Reservation> reservations;


    public Chambre(int numero, boolean disponible, Detail detail) {
        this.numero = numero;
        this.disponible = disponible;
        this.detail = detail;
        //this.reservations = new ArrayList<>();
    }

//    public List<Reservation> getReservations(){
//        return this.reservations;
//    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public  Detail getDetail(){
        return this.detail;
    }


    public abstract String toString();

}
