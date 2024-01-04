package main.java;

import main.java.Interface.ReservationManagerInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Facture implements Serializable {
    private static final long serialVersionUID = -2446852111691044855L;
    private int prixTotal;
    private Client client;

    private final ReservationManagerInterface reservation;

    public Facture(Client client, ReservationManagerInterface reservation){
        this.reservation = reservation;
        this.client = client;
        List<Reservation> reservationClient = new ArrayList<>();
        reservationClient = reservation.listReservationByClient(client);
        reservationClient
                .forEach(reservation1 ->
                        prixTotal += reservation1.getChambre().getDetail().getValeur());
    }
}
