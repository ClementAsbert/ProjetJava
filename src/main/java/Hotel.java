package main.java;

import main.java.Exception.ChambreNonDisponibleException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {

    private String nom;

    private final List<Chambre> listChambre = new ArrayList<>();

    private final List<Client> listClient = new ArrayList<>();

    private final List<Reservation> listReservation = new ArrayList<>();

    public Hotel(String nom){
        this.nom = nom;
    }

    public void addChambre(Chambre chambre){
        this.listChambre.add(chambre);
    }

    public void addReservation(Reservation reservation){
        this.listReservation.add(reservation);
    }

    public void chambreDisponible(){
        System.out.println("Chmabre disponible : ");
        for(Chambre chambre : listChambre){
            if(chambre.isDisponible()){
                System.out.println(chambre.getDetail());
            }
        }
    }

    public List<Chambre> chambreDisponibleList(){
        List<Chambre> chambres = new ArrayList<>();
        for(Chambre chambre : listChambre){
            if(chambre.isDisponible()){
                chambres.add(chambre);
            }
        }
        return chambres;
    }

    public void effectuerReservation(Client client, Chambre chambre, Date dateDebut, Date dateFin) throws ChambreNonDisponibleException {
        if(chambre.isDisponible()){
            Reservation reservation = new Reservation(dateDebut, dateFin, chambre);
            listReservation.add(reservation);
            chambre.setDisponible(false);
            client.addReservation(reservation);
            System.out.println("Reservation affectué avec succès");
        }else{
            throw new ChambreNonDisponibleException();
        }
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Chambre> getListChambre() {
        return listChambre;
    }

    public List<Client> getListClient() {
        return listClient;
    }

    public List<Reservation> getListReservation() {
        return listReservation;
    }
}
