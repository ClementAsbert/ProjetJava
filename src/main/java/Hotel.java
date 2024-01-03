package main.java;

import main.java.Exception.ChambreNonDisponibleException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel implements Serializable {

    private String nom;

    private List<Chambre> listChambre = new ArrayList<>();

    private final List<Client> listClient = new ArrayList<>();

    private final List<Reservation> listReservation = new ArrayList<>();

    public Hotel(String nom) throws Throwable {
        this.nom = nom;
        creerChambreList();
    }

    public void creerChambreList() throws Throwable {
       for(int i = 1; i<=10;i++){
           ChambreFactory factory = new ConcreteChambreFactory();
           this.listChambre.add(factory.build(ChambreFactory.SIMPLE, i));
           this.listChambre.add(factory.build(ChambreFactory.DOUBLE, i + 10));
           this.listChambre.add(factory.build(ChambreFactory.LUXESIMPLE, i + 20));
           this.listChambre.add(factory.build(ChambreFactory.LUXDOUBLE, i + 30));
       }
    }

    public void addReservation(Reservation reservation){
        this.listReservation.add(reservation);
    }

    public void chambreDisponible(){
        System.out.println("Chambre disponible : ");
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

    public static void saveHotel(Hotel hotel){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Sauvegarde"))){
            os.writeObject(hotel);
            System.out.println("save");
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static Hotel chargerHotel(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Sauvegarde"))){
            System.out.println("récupération");
           return (Hotel) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
