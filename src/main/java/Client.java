package main.java;

import java.io.Serializable;

public class Client implements Serializable {
    private static final long serialVersionUID = 2157497653279561812L;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTel;

    //private List<Reservation> listReservation = new ArrayList<>();

    public Client(String nom, String prenom, String email, String numeroTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numeroTel = numeroTel;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

//    public List<Reservation> getListReservation(){
//        return this.listReservation;
//    }
//
//    public void addReservation(Reservation reservation){
//        this.listReservation.add(reservation);
//    }
//
//    public void deleteReservation(Reservation reservation){
//        this.listReservation.remove(reservation);
//    }
}
