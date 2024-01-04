package main.java;

import main.java.Enum.Detail;
import main.java.Exception.ChambreNonDisponibleException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hotel implements Serializable {
    private static final long serialVersionUID = 3989889470389150140L;
    private String nom;

    private List<Chambre> listChambre;

    private final List<Client> listClient;

    private List<Reservation> listReservation;

    public Hotel(String nom) throws Throwable {
        this.nom = nom;
        this.listChambre = new ArrayList<>();
        this.listClient = new ArrayList<>();
        this.listReservation = new ArrayList<>();
        creerChambreList();
    }

    public void creerChambreList() throws Throwable {
       for(int i = 1; i<=2;i++){
           ChambreFactory factory = new ConcreteChambreFactory();
           this.listChambre.add(factory.build(ChambreFactory.SIMPLE, i));
           this.listChambre.add(factory.build(ChambreFactory.DOUBLE, i + 10));
           this.listChambre.add(factory.build(ChambreFactory.LUXESIMPLE, i + 20));
           this.listChambre.add(factory.build(ChambreFactory.LUXDOUBLE, i + 30));
       }
    }


    public Optional<Chambre> getFirstChambreDispoByType(Date dateDebut, Date dateFin, Detail detail){
        return this.listChambre.stream()
                .filter(chambre -> chambre.getDetail().equals(detail))
                .filter(chambre -> this.listReservation
                        .stream()
                        //Regarde si la chambre est disponible dans l'intervale et que les date ne ce chevauche pas
                        .allMatch(reservation ->reservation.getChambre() == chambre && dateFin.before(reservation.getDateDebut()) || dateDebut.after(reservation.getDateFin())))
                .findFirst();
    }

    public void effectuerReservation(Client client, Detail type, Date dateDebut, Date dateFin) throws ChambreNonDisponibleException {
        Optional<Chambre> chambre = this.getFirstChambreDispoByType(dateDebut,dateFin,type);
        if(chambre.isEmpty()){
            throw new ChambreNonDisponibleException();
        }else {
            Reservation reservation = new Reservation(dateDebut,dateFin,chambre.get(),client);
            this.listReservation.add(reservation);
            System.out.println("Reservation effectuer avec succès");
            System.out.println(chambre.get().getNumero());
        }
    }

    public void modifReservation(int id, Date newDateDebut, Date newDateFin ){
        Optional<Reservation> reservationToModify = this.listReservation.stream()
                .filter(reservation -> reservation.getId() == id )
                .findFirst();

        reservationToModify.ifPresent(reservation -> {
            reservation.setDateDebut(newDateDebut);
            reservation.setDateFin(newDateFin);
        });
    }

    public List<Reservation> listReservationByClient(Client client){
        return this.listReservation.stream()
                .filter(reservation -> reservation.getClient() == client)
                .collect(Collectors.toList());
    }

    public void deleteReservation(int id){
        this.listReservation.removeIf(reservation -> reservation.getId() == id);
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

    public void addClient(Client client){
        this.listClient.add(client);
    }

    public Optional<Client> findClient(String nom){
       return this.listClient.stream().filter(client -> client.getNom().equalsIgnoreCase(nom)).findFirst();
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
