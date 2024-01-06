package main.java;

import java.io.Serializable;
import main.java.Enum.TypeRepas;
public class Repas implements Serializable {
    private TypeRepas typeRepas;
    private Reservation reservation;

    public Repas(TypeRepas typeRepas, Reservation reservation) {
            this.typeRepas = typeRepas;
            this.reservation = reservation;
        }

    public TypeRepas getTypeRepas() {
        return typeRepas;
    }

    public void setTypeRepas(TypeRepas typeRepas) {
        this.typeRepas = typeRepas;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
