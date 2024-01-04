package main.java;

import main.java.Interface.ClientManagerInterface;
import main.java.Interface.PersistenceManagerInterface;
import main.java.Interface.ReservationManagerInterface;

import java.io.Serializable;
import java.util.List;

public class Hotel implements Serializable {
    private static final long serialVersionUID = 3989889470389150140L;
    private String nom;

    private final PersistenceManagerInterface persistenceManager;
    private final ReservationManagerInterface reservationManager;
    private final ClientManagerInterface clientManager;

    public Hotel(String nom,ChambreFactory factory,PersistenceManagerInterface persistance, ReservationManagerInterface reservation, ClientManagerInterface clientManager) throws Throwable {
        this.nom = nom;
        this.persistenceManager = persistance;
        this.clientManager = clientManager;
        this.reservationManager = reservation;
        reservation.creerChambreList(factory);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void saveHotel(){
        persistenceManager.saveHotel(this);
    }


    public PersistenceManagerInterface getPersistenceManager() {
        return persistenceManager;
    }

    public ReservationManagerInterface getReservationManager() {
        return reservationManager;
    }

    public ClientManagerInterface getClientManager() {
        return clientManager;
    }
}
