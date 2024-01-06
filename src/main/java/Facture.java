package main.java;

import main.java.Interface.ReservationManagerInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Facture implements Serializable {
    private static final long serialVersionUID = -2446852111691044855L;
    private int prixTotal;
    private Client client;
    private Reservation reservation;

    public Facture(Client client, Reservation reservation){
        this.reservation = reservation;
        this.client = client;
        this.prixTotal = this.calculFacture();
    }

    private int calculFacture(){
        int prixRepas = this.reservation.getCommandes().stream()
                .flatMap(commande -> commande.getListRepa().stream())
                .map(repas -> repas.getTypeRepas().getPrix())
                .reduce(0, Integer::sum);
        int prixChambre = this.reservation.getChambre().getDetail().getValeur();
        return prixChambre + prixRepas;
    }

    @Override
    public String toString() {
        return "Votre facture s'élève à : " + this.prixTotal + " €";
    }
}
